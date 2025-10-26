package com.sanofi.pharma.dto.request;

import lombok.Data;

/**
 * @author ELLE Q
 * @since 2025-10-26
 */
@Data
public class AuditLogSearchRequest {

    private String patientId;

    private String pharmacyId;

    private String status;
}
