package com.sanofi.pharma.enums;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
public enum FailureCode {
    VALIDATOR_FAIL("VALIDATOR_FAIL", "validator fail: {0} - {1}"),
    SYSTEM_ERROR("SYSTEM_ERROR", "system error: {0}"),
    SUBMIT_FAIL("SUBMIT_FAIL", "submit fail: {0}"),
    FULFILL_FAIL("FULFILL_FAIL", "fulfill fail"),
    INSUFFICIENT_STOCK("INSUFFICIENT_STOCK", "insufficient stock: {0}"),
    PRESCRIPTION_EXPIRED("PRESCRIPTION_EXPIRED", "prescription-{0} expired"),
    PRESCRIPTION_NOT_SUBMITTABLE("PRESCRIPTION_NOT_SUBMITTABLE", "prescription not submittable : status is not in (FAILED, CANCELLED)"),
    PRESCRIPTION_NOT_FULFILLABLE("PRESCRIPTION_NOT_FULFILLABLE", "prescription not fulfillable : status is not submitted"),
    DRUG_EXPIRED("DRUG_EXPIRED", "drug expired"),
    DRUG_NOT_CONTRACTED("DRUG_NOT_CONTRACTED", "drug not contracted"),
    DRUG_NOT_FOUND("DRUG_NOT_FOUND", "drug not found"),
    DRUG_OUT_STOCK("DRUG_OUT_STOCK", "DRUG_OUT_STOCK"),
    EXCEEDS_ALLOCATION_LIMIT("EXCEEDS_ALLOCATION_LIMIT", "exceeds allocation limit: {0}"),
    CONCURRENT_MODIFICATION("CONCURRENT_MODIFICATION", "concurrent modification error: {0}");

    private final String code;
    private final String message;

    FailureCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
