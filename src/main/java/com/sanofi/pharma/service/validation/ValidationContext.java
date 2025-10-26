package com.sanofi.pharma.service.validation;

import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.enums.OperationType;
import lombok.Data;

/**
 * Context for prescription validation in a chain
 *
 * @author ELLE Q
 * @since 2025-10-25
 */
@Data
public class ValidationContext {

    private ValidationResult result;

    private OperationType operationType;

    public ValidationContext(OperationType operationType) {
        this.result = new ValidationResult();
        this.operationType = operationType;
    }

    public boolean hasFailures() {
        return !result.passed();
    }

    public ValidationResult getValidationResult() {
        return result;
    }

    public void addFailureReason(FailureReason failureReason) {
        if (result != null) {
            result.getFailureReasons().add(failureReason);
        }
    }
}
