package com.sanofi.pharma.entity;

import lombok.Data;

/**
 *  TABLE: pharmacy
 */
@Data
public class Pharmacy {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String manager;
}
