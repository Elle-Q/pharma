package com.sanofi.pharma.enums;


public enum ExceptionCode {

    SUCCESS("0", "success"),
    FAIL("-1", "success"),
    SYSTEM_ERROR("500", "system error"),

    //manufacturer code (start with 200000)
    MANUFACTURER_NOT_EXIST("200000", "manufacturer not exist: {0}"),

    //pharmacy code (start with 300000)

    //prescription code (start with 400000)
    PRESCRIPTION_EXPIRED("4000001", "prescription expired"),
    PRESCRIPTION_NOT_RESUBMITTABLE("4000001", "prescription cant be resubmitted, current status: {0}");

    //contract code (start with 500000)

    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionCode fromCode(String code) {
        for (ExceptionCode c : ExceptionCode.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return SYSTEM_ERROR;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
