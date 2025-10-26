package com.sanofi.pharma.service.validation;

import com.sanofi.pharma.dto.common.FailureReason;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationResult for prescription submit & fulfill
 *
 * @author ELLE Q
 * @since 2025-10-25
 */
@Data
public class ValidationResult {

    private List<FailureReason> failureReasons;

    public ValidationResult() {
        this.failureReasons = new ArrayList<>();
    }

    public boolean passed() {
        return failureReasons.isEmpty();
    }

}
