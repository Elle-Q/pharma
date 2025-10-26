package com.sanofi.pharma.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Data
public class PrescriptionItemRequest {

    @NotNull(message = "drugId can't be null")
    private Long drugId;

    @NotBlank(message = "drugName can't be null")
    private String drugName;

    @NotBlank(message = "dosage can't be null")
    private String dosage;

    @NotNull(message = "amount can't be null")
    private Integer amount;

    @NotBlank(message = "instruction can't be null")
    private String instruction;

}
