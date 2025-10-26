package com.sanofi.pharma.entity;

import com.sanofi.pharma.enums.PrescriptionStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  TABLE: prescription
 */
@Data
public class Prescription {
    private Long id;
    private String number;
    private Long pharmacyId;
    private String doctorName;
    private String pharmacistName;
    private Long patientId;
    private String diagnosis;
    private String status;
    private LocalDateTime fulfilledTime;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer version;

    /**
     * Check if prescription can be resubmitted
     * Resubmission is allowed from the following states:
     * - CANCELLED
     * - FAILED
     * @return true if prescription is in a state that allows resubmission
     */
    public boolean canResubmit() {
        PrescriptionStatus currentStatus = PrescriptionStatus.fromCode(status);
        return currentStatus.equals(PrescriptionStatus.CANCELLED) || currentStatus.equals(PrescriptionStatus.FAILED);
    }

    public boolean isExpired() {
        return expireTime.isBefore(LocalDateTime.now());
    }

    public boolean canFULFILL() {
        PrescriptionStatus currentStatus = PrescriptionStatus.fromCode(status);
        return currentStatus.equals(PrescriptionStatus.SUBMITTED);
    }
}
