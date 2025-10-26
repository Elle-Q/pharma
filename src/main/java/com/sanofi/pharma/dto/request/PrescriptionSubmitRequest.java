package com.sanofi.pharma.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Data
public class PrescriptionSubmitRequest {

    @NotBlank(message = "number can't be null")
    @Size(max = 64, message = "cannot exceed 64 characters")
    private String number;

    @NotNull(message = "pharmacyId can't be null")
    private Long pharmacyId;

    @NotBlank(message = "doctorName can't be null")
    @Size(max = 64, message = "cannot exceed 64 characters")
    private String doctorName;

    @NotBlank(message = "pharmacistName can't be null")
    @Size(max = 64, message = "cannot exceed 64 characters")
    private String pharmacistName;

    @NotNull(message = "pharmacyId can't be null")
    private Long patientId;

    @NotBlank(message = "patient_name can't be null")
    @Size(max = 64, message = "cannot exceed 64 characters")
    private String patientName;

    @NotBlank(message = "diagnosis can't be null")
    private String diagnosis;

    @NotNull(message = "prescription createTime can't be null")
    @PastOrPresent(message = "prescription createTime can't be future date")
    private LocalDateTime createTime;

    @NotNull(message = "validDays can't be null")
    @Min(value = 1, message = "validDays must be greater than 0")
    private Integer validDays;

    @Valid
    @NotEmpty(message = "at least one item{drug) included")
    List<PrescriptionItemRequest> items;

    public boolean isExpired() {
        return createTime.plusDays(validDays).isBefore(LocalDateTime.now());
    }
}
