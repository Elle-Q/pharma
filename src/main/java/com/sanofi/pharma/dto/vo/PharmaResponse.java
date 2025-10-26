package com.sanofi.pharma.dto.vo;

import com.sanofi.pharma.enums.ExceptionCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ELLE Q
 * @since 2025-10-24
 */
@Data
public class PharmaResponse<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public PharmaResponse() {
    }

    public PharmaResponse(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public PharmaResponse(String code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public PharmaResponse(ExceptionCode code) {
        this();
        this.code = code.getCode();
        this.msg = code.getMessage();
    }

    public PharmaResponse(ExceptionCode code, T data) {
        this(code);
        this.data = data;
    }

    public static <T> PharmaResponse<T> ok() {
        return new PharmaResponse<>(ExceptionCode.SUCCESS);
    }

    public static <T> PharmaResponse<T> ok(T data) {
        return new PharmaResponse<>(ExceptionCode.SUCCESS, data);
    }

    public static <T> PharmaResponse<T> fail(ExceptionCode code) {
        return new PharmaResponse<>(code);
    }

    public static <T> PharmaResponse<T> fail(String message) {
        return new PharmaResponse<>(ExceptionCode.SYSTEM_ERROR.getCode(), message);
    }

    public static <T> PharmaResponse<T> fail(String code, String message) {
        return new PharmaResponse<>(code, message);
    }

    public static <T> PharmaResponse<T> fail(ExceptionCode code, String message) {
        return new PharmaResponse<>(code.getCode(), message);
    }

    public static <T> PharmaResponse<T> fail(T data) {
        return new PharmaResponse<>(ExceptionCode.SUCCESS, data);
    }

    @Override
    public String toString() {
        return "PharmaResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
