package com.sanofi.pharma.entity;

import lombok.Data;

/**
 *  TABLE: prescription_item
 */
@Data
public class PrescriptionItem {
    private Long id;
    private Long prescriptionId;
    private Long pharmacyId;
    private Long drugId;
    private String drugName;
    private String dosage;
    private Integer amount;
    private String instruction;
}
