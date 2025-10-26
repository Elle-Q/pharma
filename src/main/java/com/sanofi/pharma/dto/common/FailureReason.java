package com.sanofi.pharma.dto.common;

import com.sanofi.pharma.entity.Drug;
import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.FailureCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@Data
@NoArgsConstructor
public class FailureReason {
    private String code;
    private String message;
    private HashMap<String, Object> detail;

    public FailureReason(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public FailureReason(String code, String message, HashMap<String, Object> detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static FailureReason fail0(FailureCode failureCode, Object... args) {
        return new FailureReason(failureCode.getCode(), MessageFormat.format(failureCode.getMessage(), args));
    }


    public static FailureReason fail(FailureCode failureCode, Prescription prescription) {
        HashMap<String, Object> detail = new HashMap<>();
        switch (failureCode) {
            case PRESCRIPTION_EXPIRED, PRESCRIPTION_NOT_SUBMITTABLE, PRESCRIPTION_NOT_FULFILLABLE -> {
                detail.put("number", prescription.getNumber());
                detail.put("pharmacy_id", prescription.getPharmacyId());
                detail.put("patient_id", prescription.getPatientId());
                detail.put("doctor_name", prescription.getDoctorName());
                detail.put("pharmacist_name", prescription.getPharmacistName());
                detail.put("status", prescription.getStatus());
                detail.put("expire_time", prescription.getExpireTime());
                detail.put("fulfilled_time", prescription.getFulfilledTime());
            }
        }
        return new FailureReason(failureCode.getCode(), failureCode.getMessage(), detail);
    }

    public static FailureReason fail(FailureCode failureCode, PrescriptionItem item) {
        HashMap<String, Object> detail = new HashMap<>();
        switch (failureCode) {
            case DRUG_NOT_CONTRACTED, DRUG_NOT_FOUND -> {
                detail.put("drug_id", item.getDrugId());
                detail.put("drug_name", item.getDrugName());
            }
        }
        return new FailureReason(failureCode.getCode(), failureCode.getMessage(), detail);
    }


    public static FailureReason fail(FailureCode failureCode, Drug drug) {
        HashMap<String, Object> detail = new HashMap<>();
        switch (failureCode) {
            case DRUG_EXPIRED -> {
                detail.put("drug_id", drug.getId());
                detail.put("drug_name", drug.getName());
                detail.put("batch_number", drug.getBatchNumber());
                detail.put("manufacturer_id", drug.getManufacturerId());
                detail.put("expiry_date", drug.getExpiryDate());
            }
            case DRUG_OUT_STOCK -> {
                detail.put("drug_id", drug.getId());
                detail.put("drug_name", drug.getName());
                detail.put("batch_number", drug.getBatchNumber());
                detail.put("manufacturer_id", drug.getManufacturerId());
                detail.put("expiry_date", drug.getExpiryDate());
                detail.put("stock", drug.getStock());
            }
        }
        return new FailureReason(failureCode.getCode(), failureCode.getMessage(), detail);
    }


    public static FailureReason fail(FailureCode failureCode, ContractDrug contractDrug, PrescriptionItem item) {
        HashMap<String, Object> detail = new HashMap<>();
        switch (failureCode) {
            case EXCEEDS_ALLOCATION_LIMIT -> {
                detail.put("drug_id", item.getDrugId());
                detail.put("drug_name", item.getDrugName());
                detail.put("request_amount", item.getAmount());
                detail.put("allocated_amount", contractDrug.getAllocatedAmount());
                detail.put("used_amount", contractDrug.getUsedAmount());
                detail.put("remaining_amount", contractDrug.getRemainingAmout());
            }
        }
        return new FailureReason(failureCode.getCode(), failureCode.getMessage(), detail);
    }

}
