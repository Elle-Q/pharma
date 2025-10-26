package com.sanofi.pharma.exception;

import com.sanofi.pharma.enums.ExceptionCode;
import lombok.Data;

/**
 * @author ELLE Q
 * @since 2025-10-24
 */
@Data
public class BizException extends RuntimeException {
    private String code;

    public BizException(ExceptionCode exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
    }

    public BizException(ExceptionCode exception, Object... args) {
        super(String.format(exception.getMessage(), args));
        this.code = exception.getCode();
    }
}
