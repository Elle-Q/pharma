package com.sanofi.pharma.dto.common;

import lombok.Data;

import java.time.LocalDate;

/**
 *
 * @author ELLE Q
 * @since 2025-10-25
 */
@Data
public class ContractDrug {

    private Long drugId;

    private String name;

    private String batchNumber;

    private String manufacturer;

    private String description;

    private LocalDate expiryDate;

    private Integer allocatedAmount;

    private Integer usedAmount;

    public int getRemainingAmout() {
        return allocatedAmount - usedAmount;
    }
}
