package com.sanofi.pharma.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TABLE: audit_log
 * Audit Log
 * @author ELLE Q
 * @since 2025-10-25
 */
@Data
public class AuditLog {
    private Long id;
    private String operationType;
    private Long prescriptionId;
    private Long patientId;
    private Long pharmacyId;
    private String operatorName;
    private String status;
    private String drugsRequested;
    private String drugsDispensed;
    private String failureReasons;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
