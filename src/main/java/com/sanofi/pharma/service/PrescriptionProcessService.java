package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.dto.request.PrescriptionItemRequest;
import com.sanofi.pharma.dto.request.PrescriptionSubmitRequest;
import com.sanofi.pharma.dto.vo.PrescriptionProcessResult;
import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.OperationCode;
import com.sanofi.pharma.enums.OperationType;
import com.sanofi.pharma.exception.OptimisticLockException;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.ValidationResult;
import com.sanofi.pharma.service.validation.validator.Validator;
import com.sanofi.pharma.service.validation.validator.ValidatorChainBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PrescriptionProcessService {

    private final PrescriptionService prescriptionService;

    private final AuditLogService auditLogService;

    private final ValidatorChainBuilder validatorChainBuilder;

    private final static int MAX_RETRY = 5;
    private final static Long SLEEP_MILLIS = 500L;

    /**
     * Submit a prescription for a patient at a specific pharmacy
     */
    public PrescriptionProcessResult submit(PrescriptionSubmitRequest request) {
        //1. build PrescriptionDetail
        PrescriptionDetail prescriptionDetail = buildPrescriptionDetail(request);
        return doSubmit(prescriptionDetail);
    }

    /**
     * Submit a prescription by prescriptionId
     */
    public PrescriptionProcessResult submitById(Long prescriptionId) {
        //1. get PrescriptionDetail
        PrescriptionDetail prescriptionDetail = getPrescriptionDetail(prescriptionId);
        return doSubmit(prescriptionDetail);
    }

    /**
     * Fulfill a prescription
     */

    public PrescriptionProcessResult fulfill(Long prescriptionId) {
        log.info("start to fulfill prescription: {}", prescriptionId);

        int retryCount = 0;

        while (retryCount < MAX_RETRY) {
            //1. get PrescriptionDetail
            PrescriptionDetail prescriptionDetail = getPrescriptionDetail(prescriptionId);
            ValidationContext context = new ValidationContext(OperationType.FULFILL);
            try {
                //2. run all validators
                ValidationResult validationResult = runValidators(prescriptionDetail, context);

                //3. if has failures -> audit log -> return
                if (context.hasFailures()) {
                    auditLogService.createOnFail(OperationCode.PRESCRIPTION_FULFILL_FAIL, prescriptionDetail, validationResult.getFailureReasons());
                    return PrescriptionProcessResult.fail(validationResult.getFailureReasons());
                }

                //4. fulfill
                prescriptionService.fulfillPrescription(prescriptionDetail, context);
                if (context.hasFailures()) {
                    auditLogService.createOnFail(OperationCode.PRESCRIPTION_FULFILL_FAIL, prescriptionDetail, validationResult.getFailureReasons());
                    return PrescriptionProcessResult.fail(validationResult.getFailureReasons());
                }

                //5. audit log
                auditLogService.createOnSuccess(OperationCode.PRESCRIPTION_FULFILL_SUCCESS, prescriptionDetail);
                return PrescriptionProcessResult.success();

            } catch (OptimisticLockException e) {
                log.warn("Optimistic Lock conflict with Prescription {}, retry {}/{}", prescriptionId, retryCount, MAX_RETRY);
                if (retryCount == MAX_RETRY - 1) {
                    // run out of retry count. return fail
                    return fulfillErrorReturn(FailureCode.CONCURRENT_MODIFICATION, prescriptionDetail, e.getMessage(), context);
                }
                retryCount++;
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException ex) {
                    return fulfillErrorReturn(FailureCode.SYSTEM_ERROR, prescriptionDetail, ex.getMessage(), context);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return fulfillErrorReturn(FailureCode.SYSTEM_ERROR, prescriptionDetail, e.getMessage(), context);
            }
        }
        return null;
    }


    private PrescriptionProcessResult doSubmit(PrescriptionDetail prescriptionDetail) {
        log.info("start to submit prescription...");
        ValidationContext context = new ValidationContext(OperationType.SUBMIT);
        try {
            //1. run all validators
            ValidationResult validationResult = runValidators(prescriptionDetail, context);

            //2. if has failures -> audit log -> return
            if (context.hasFailures()) {
                auditLogService.createOnFail(OperationCode.PRESCRIPTION_SUBMIT_FAIL, prescriptionDetail, validationResult.getFailureReasons());
                return PrescriptionProcessResult.fail(validationResult.getFailureReasons());
            }

            //3. submit
            prescriptionService.submitPrescription(prescriptionDetail);

            //audit log
            log.info("success to submit prescription {}. save audit log", prescriptionDetail.getPrescription().getNumber());
            auditLogService.createOnSuccess(OperationCode.PRESCRIPTION_SUBMIT_SUCCESS, prescriptionDetail);
            return PrescriptionProcessResult.success(prescriptionDetail.getPrescription().getId());

        } catch (Exception e) {
            //system error
            context.addFailureReason(FailureReason.fail0(FailureCode.SUBMIT_FAIL, e.getMessage()));
            auditLogService.createOnFail(OperationCode.PRESCRIPTION_SUBMIT_FAIL, prescriptionDetail, context.getValidationResult().getFailureReasons());
            log.info("fail to submit prescription due to {}", e.getMessage());
            e.printStackTrace();
        }
        return PrescriptionProcessResult.fail(context.getValidationResult().getFailureReasons());
    }


    private PrescriptionProcessResult fulfillErrorReturn(FailureCode failureCode,
                                                         PrescriptionDetail prescriptionDetail,
                                                         String message, ValidationContext context) {
        auditLogService.createOnFail(OperationCode.PRESCRIPTION_FULFILL_FAIL, prescriptionDetail, context.getValidationResult().getFailureReasons());
        context.addFailureReason(FailureReason.fail0(failureCode, message));
        return PrescriptionProcessResult.fail(context.getValidationResult().getFailureReasons());
    }


    private PrescriptionDetail getPrescriptionDetail(Long prescriptionId) {
        Prescription prescription = prescriptionService.findById(prescriptionId);
        List<PrescriptionItem> items = prescriptionService.findItemsByPrescriptionId(prescriptionId);
        items.forEach(item -> item.setPharmacyId(prescription.getPharmacyId()));
        return new PrescriptionDetail(prescription, items);
    }


    private ValidationResult runValidators(PrescriptionDetail prescriptionDetail, ValidationContext context) {
        //run all validators
        Validator<PrescriptionDetail> chain = validatorChainBuilder.buildValidationChain();
        return chain.validate(prescriptionDetail, context);
    }


    /**
     * create Prescription from request
     * 1. submit new -> if prescription existed
     * 1. resubmit -> if prescription existed
     *
     * @return Prescription
     */
    private Prescription convertToPrescription(PrescriptionSubmitRequest request) {
        Prescription prescription = new Prescription();
        prescription.setNumber(request.getNumber());
        prescription.setPharmacyId(request.getPharmacyId());
        prescription.setDoctorName(request.getDoctorName());
        prescription.setPharmacistName(request.getPharmacistName());
        prescription.setPatientId(request.getPatientId());
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setExpireTime(request.getCreateTime().plusDays(request.getValidDays()));
        prescription.setCreateTime(LocalDateTime.now());
        prescription.setUpdateTime(LocalDateTime.now());
        return prescription;
    }


    private List<PrescriptionItem> convertToPrescriptionItem(List<PrescriptionItemRequest> itemRequests, Long pharmacyId) {
        List<PrescriptionItem> items = new ArrayList<>();
        for (PrescriptionItemRequest itemRequest : itemRequests) {
            PrescriptionItem item = new PrescriptionItem();
            item.setDrugId(itemRequest.getDrugId());
            item.setDrugName(itemRequest.getDrugName());
            item.setDosage(itemRequest.getDosage());
            item.setAmount(itemRequest.getAmount());
            item.setInstruction(itemRequest.getInstruction());
            item.setPharmacyId(pharmacyId);
            items.add(item);
        }
        return items;
    }

    private PrescriptionDetail buildPrescriptionDetail(PrescriptionSubmitRequest request) {

        Prescription prescription = convertToPrescription(request);
        List<PrescriptionItem> items = convertToPrescriptionItem(request.getItems(), request.getPharmacyId());
        return new PrescriptionDetail(prescription, items);
    }

}
