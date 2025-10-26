package com.sanofi.pharma.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * //TODO: Rename Contract to a proper name (pharmacy_drug_allocation)
 * TABLE: contract
 * Pharmacy and drug contracts
 * Pharmacies can only dispense specific drugs they are contracted for.
 */
@Data
public class Contract {

    private Long contractId;

    private Long pharmacyId;

    private Long drugId;

    private Integer allocatedAmount;

    private Integer usedAmount;

    //0 inactive -- 1 active
    private Integer status;

    //contract end time
    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;
}
