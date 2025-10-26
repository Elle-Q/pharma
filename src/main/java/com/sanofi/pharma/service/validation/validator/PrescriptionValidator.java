package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.OperationType;
import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.mapper.PrescriptionMapper;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.service.validation.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@Order(1)
@Component
public class PrescriptionValidator extends AbstractValidator<PrescriptionDetail> {

    private final PrescriptionMapper prescriptionMapper;

    @Autowired
    public PrescriptionValidator(PrescriptionMapper prescriptionMapper) {
        super(ValidatorType.PRESCRIPTION_VALIDATOR, 1);
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    protected void doValidate(PrescriptionDetail request, ValidationContext context) {
        Prescription prescription = request.getPrescription();
        //1. TODO: check prescription is valid (real && legal) -> ThirdParty Or Other API

        //2. check if prescription is expired
        if (prescription.isExpired()) {
            context.addFailureReason(FailureReason.fail0(FailureCode.PRESCRIPTION_EXPIRED, prescription.getNumber()));
            return;
        }

        // 3.1 Validation for SUBMIT
        if (context.getOperationType() == OperationType.SUBMIT) {

            //check if prescription already exist -> true ? status check
            Prescription existPrescription = prescriptionMapper.findByNumber(prescription.getNumber());

//            if (existPrescription != null && prescription.getId() == null) {
//                prescription.setId(existPrescription.getId());
//            }

            if (existPrescription != null && !existPrescription.canResubmit()) {
                context.addFailureReason(FailureReason.fail(FailureCode.PRESCRIPTION_NOT_SUBMITTABLE, existPrescription));
            }

        } else if (!prescription.canFULFILL()) {
            context.addFailureReason(FailureReason.fail(FailureCode.PRESCRIPTION_NOT_FULFILLABLE, prescription));
        }
    }

}
