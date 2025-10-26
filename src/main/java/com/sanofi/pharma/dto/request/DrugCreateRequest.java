package com.sanofi.pharma.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Data
public class DrugCreateRequest {

    @NotBlank(message = "name can't be null")
    @Size(max = 255, message = "cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "batchNumber can't be null")
    @Size(max = 64, message = "cannot exceed 64 characters")
    private String batchNumber;

    @NotNull(message = "manufacturerId can't be null")
    private Long manufacturerId;

    @NotNull(message = "stock can't be null")
    @Min(value = 0, message = "stock can't be less than 0")
    private Integer stock;

    @NotBlank(message = "description can't be null")
    private String description;

    @NotNull(message = "expiryDate can't be null")
    private LocalDate expiryDate;
}
