package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.request.PrescriptionSubmitRequest;
import com.sanofi.pharma.dto.vo.PharmaResponse;
import com.sanofi.pharma.dto.vo.PrescriptionActionResult;
import com.sanofi.pharma.service.PrescriptionProcessService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionProcessService prescriptionProcessService;

    /**
     * Submit a prescription for a patient at a specific pharmacy
     */
    @PostMapping("submit")
    public PharmaResponse<?> submit(@Valid @RequestBody PrescriptionSubmitRequest request) {
        PrescriptionActionResult result = prescriptionProcessService.submit(request);
        if (result.isSuccess()) {
            return PharmaResponse.ok();
        } else {
            return PharmaResponse.fail(result.getFailureReasons());
        }
    }

    /**
     * Submit a prescription for a patient at a specific pharmacy
     */
    @GetMapping("/{prescriptionId}/fulfill")
    public PharmaResponse<?> fulfill(@PathVariable @NotNull(message = "prescriptionId can't be null") Long prescriptionId) {
        PrescriptionActionResult result = prescriptionProcessService.fulfill(prescriptionId);
        return PharmaResponse.ok(result);
    }

    /**
     * Submit a prescription for a patient at a specific pharmacy
     */
    @GetMapping("/{prescriptionId}/submit")
    public PharmaResponse<?> submitById(@PathVariable @NotNull(message = "prescriptionId can't be null") Long prescriptionId) {
        PrescriptionActionResult result = prescriptionProcessService.submitById(prescriptionId);
        return PharmaResponse.ok(result);
    }
}
