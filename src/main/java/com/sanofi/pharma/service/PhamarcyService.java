package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.ContractDrug;
import com.sanofi.pharma.dto.common.PharmacyContractDetail;
import com.sanofi.pharma.entity.Pharmacy;
import com.sanofi.pharma.mapper.ContractMapper;
import com.sanofi.pharma.mapper.PharmacyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@RequiredArgsConstructor
@Component
public class PhamarcyService {

    private final PharmacyMapper pharmacyMapper;
    private final ContractMapper contractMapper;

    public Pharmacy getById(Long id) {
        return pharmacyMapper.getById(id);
    }

    public List<PharmacyContractDetail> getPharmaciesWithContracts() {
        List<Pharmacy> pharmacies = pharmacyMapper.findAll();
        if (CollectionUtils.isEmpty(pharmacies)) {
            return null;
        }
        return pharmacies.stream().map(pharmacy -> {
            List<ContractDrug> contractDrugs = contractMapper.findContractDrugsByPharmacyId(pharmacy.getId());
            return new PharmacyContractDetail(pharmacy, contractDrugs);
        }).toList();
    }
}
