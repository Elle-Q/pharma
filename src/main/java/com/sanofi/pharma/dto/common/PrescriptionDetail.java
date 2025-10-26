package com.sanofi.pharma.dto.common;

import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.entity.PrescriptionItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-25
 */
@AllArgsConstructor
@Data
public class PrescriptionDetail {

    Prescription prescription;

    List<PrescriptionItem> items;

}
