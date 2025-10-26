package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.ContractDrug;
import com.sanofi.pharma.entity.Contract;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.exception.OptimisticLockException;
import com.sanofi.pharma.mapper.ContractMapper;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.validator.Validator;
import com.sanofi.pharma.service.validation.validator.ValidatorChainBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author ELLE Q
 * @since 2025-10-24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ContractService {

    private final ContractMapper contractMapper;

    private final ValidatorChainBuilder validatorChainBuilder;

    public List<ContractDrug> findContractDrugsByPharmacyId(Long pharmacyId) {
        return contractMapper.findContractDrugsByPharmacyId(pharmacyId);
    }

    public ContractDrug findByPharmacyIdAndDrugId(Long pharmacyId, Long drugId) {
        return contractMapper.findDetailByPharmacyIdAndDrugId(pharmacyId, drugId);
    }

    public void updateUsageAmountWithVersion(List<PrescriptionItem> items, Long pharmacyId, ValidationContext context) {
        Validator<PrescriptionItem> chain = validatorChainBuilder.buildContractValidationChain();
        for (PrescriptionItem item : items) {

            chain.validate(item, context);
            if (context.hasFailures()) {
                break;
            }

            //update usage amount
            Contract contract = findByDrugIdPharmacyId(item.getDrugId(), pharmacyId);
            int updated = contractMapper.updateUsageAmountWithVersion(item.getDrugId(), pharmacyId, item.getAmount(), contract.getVersion());

            if (updated == 0) {
                throw new OptimisticLockException("fail to update contract by version");
            }

            log.debug("contract update usage success: drug_id: {}, pharmacy_d: {}, amount: {}", item.getDrugId(), pharmacyId, item.getAmount());
        }

    }

    public Contract findByDrugIdPharmacyId(Long drugId, Long pharmacyId) {
        return contractMapper.findByDrugIdPharmacyId(drugId, pharmacyId);
    }

}
