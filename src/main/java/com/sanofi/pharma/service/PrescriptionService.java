package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.PrescriptionStatus;
import com.sanofi.pharma.exception.OptimisticLockException;
import com.sanofi.pharma.mapper.PrescriptionItemMapper;
import com.sanofi.pharma.mapper.PrescriptionMapper;
import com.sanofi.pharma.service.validation.ValidationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PrescriptionService {

    private final PrescriptionMapper prescriptionMapper;

    private final PrescriptionItemMapper prescriptionItemMapper;

    private final DrugService drugService;

    private final ContractService contractService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void fulfillPrescription(PrescriptionDetail prescriptionDetail, ValidationContext context) {

        List<PrescriptionItem> items = prescriptionDetail.getItems();
        Prescription prescription = prescriptionDetail.getPrescription();

        //step1: deduct stoke - drug
        drugService.deductStockWithVersion(items, context);

        //step2: update contract
        contractService.updateUsageAmountWithVersion(items, prescription.getPharmacyId(), context);

        //step3: update prescription status
        updatePrescriptionWithVersion(prescription);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void submitPrescription(PrescriptionDetail prescriptionDetail) {
        Prescription prescription = prescriptionDetail.getPrescription();
        updateForSubmit(prescription);
        if (prescription.getId() != null) {
            prescriptionMapper.updateById(prescription);
        } else {
            prescriptionMapper.insert(prescription);
            // insert items
            for (PrescriptionItem item : prescriptionDetail.getItems()) {
                item.setPrescriptionId(prescription.getId());
                prescriptionItemMapper.insert(item);
            }
        }
    }

    public void updatePrescriptionWithVersion(Prescription prescription) {
        prescription = prescriptionMapper.findById(prescription.getId());
        prescription.setStatus(PrescriptionStatus.FULFILLED.getCode());
        prescription.setUpdateTime(LocalDateTime.now());

        int updated = prescriptionMapper.updateWithWithVersion(prescription);
        if (updated == 0) {
            throw new OptimisticLockException("fail to update prescription by version");
        }
    }

    /**
     * if prescription is to be resubmitted, update below info
     * - status
     * - update_time
     */
    private void updateForSubmit(Prescription prescription) {
        prescription.setStatus(PrescriptionStatus.SUBMITTED.getCode());
        prescription.setUpdateTime(LocalDateTime.now());
    }

    public Prescription findById(Long prescriptionId) {
        return prescriptionMapper.findById(prescriptionId);
    }

    public List<PrescriptionItem> findItemsByPrescriptionId(Long prescriptionId) {
        return prescriptionItemMapper.findByPrescriptionId(prescriptionId);
    }
}
