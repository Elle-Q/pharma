package com.sanofi.pharma.enums;

public enum PrescriptionStatus {
    SUBMITTED("SUBMITTED"),
    FULFILLING("FULFILLING"),
    FULFILLED("FULFILLED"),
    PARTIALLY_FULFILLED("PARTIALLY_FULFILLED"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED");

    private final String code;

    PrescriptionStatus(String code) {
        this.code = code;
    }

    public static PrescriptionStatus fromCode(String code) {
        for (PrescriptionStatus c : PrescriptionStatus.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        throw new IllegalArgumentException("invalid PrescriptionStatus code: " + code);
    }
    public String getCode() {
        return code;
    }
}
