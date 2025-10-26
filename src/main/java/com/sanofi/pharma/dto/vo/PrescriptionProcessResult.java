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
public class PrescriptionProcessResult {

    private boolean success;

    private Object data;

    private List<FailureReason> failureReasons;

    public PrescriptionProcessResult(boolean success) {
        this.success = success;
    }

    public PrescriptionProcessResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public PrescriptionProcessResult(boolean success, List<FailureReason> failureReasons) {
        this.success = success;
        this.failureReasons = failureReasons;
    }

    public static PrescriptionProcessResult fail(List<FailureReason> failureReasons) {
        return new PrescriptionProcessResult(false, failureReasons);
    }

    public static PrescriptionProcessResult success() {
        return new PrescriptionProcessResult(true);
    }

    public static PrescriptionProcessResult success(Object data) {
        return new PrescriptionProcessResult(true, data);
    }

}
