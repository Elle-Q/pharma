package com.sanofi.pharma.exception;


/**
 *
 * @author ELLE Q
 * @since 2025-10-26
 */
public class OptimisticLockException extends RuntimeException {

    public OptimisticLockException(String message) {
        super(message);
    }
}
