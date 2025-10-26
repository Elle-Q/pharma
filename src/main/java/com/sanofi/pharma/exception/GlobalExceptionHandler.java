package com.sanofi.pharma.exception;

import com.sanofi.pharma.enums.ExceptionCode;
import com.sanofi.pharma.dto.vo.PharmaResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handle with base biz exceptions @ExceptionCode
     * @param e exception
     */
    @ExceptionHandler(BizException.class)
    public PharmaResponse<String> handleBaseException(BizException e) {
        log.warn("Biz Exception - Code: {}, Message: {}", e.getCode(), e.getMessage());
        return PharmaResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * handle validation exceptions (error in @Valid @RequestBody)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public PharmaResponse<String> handleException(MethodArgumentNotValidException e) {
        log.error("validation error: ", e);
        return PharmaResponse.fail(ExceptionCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * handle validation exceptions (error in @RequestParam、@PathVariable)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public PharmaResponse<String> handleException(ConstraintViolationException e) {
        log.error("validation error: ", e);
        return PharmaResponse.fail(ExceptionCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * handle other exceptions
     */
    @ExceptionHandler(Exception.class)
    public PharmaResponse<String> handleException(Exception e) {
        log.error("system error: ", e);
        return PharmaResponse.fail(ExceptionCode.SYSTEM_ERROR, e.getMessage());
    }
}
