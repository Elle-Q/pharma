package com.sanofi.pharma.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TABLE: drug
 */
@Data
public class Drug {

    private Long id;

    private String name;

    private String batchNumber;

    private Long manufacturerId;

    private Integer stock;

    private String description;

    private LocalDate expiryDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    /**
     * if drug is expired
     */
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    /**
     * if stock is sufficient
     */
    public boolean isSufficient(int requiredAmount) {
        return stock >= requiredAmount;
    }
}
