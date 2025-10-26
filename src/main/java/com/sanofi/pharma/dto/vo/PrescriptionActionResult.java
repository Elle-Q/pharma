package com.sanofi.pharma.dto.vo;

import com.sanofi.pharma.dto.common.FailureReason;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-26
 */
@Data
public class PrescriptionActionResult {

    private boolean success;

    private Object data;

    private List<FailureReason> failureReasons;

    public PrescriptionActionResult(boolean success) {
        this.success = success;
    }

    public PrescriptionActionResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public PrescriptionActionResult(boolean success, List<FailureReason> failureReasons) {
        this.success = success;
        this.failureReasons = failureReasons;
    }

    public static PrescriptionActionResult fail(List<FailureReason> failureReasons) {
        return new PrescriptionActionResult(false, failureReasons);
    }

    public static PrescriptionActionResult success() {
        return new PrescriptionActionResult(true);
    }

    public static PrescriptionActionResult success(Object data) {
        return new PrescriptionActionResult(true, data);
    }

}
