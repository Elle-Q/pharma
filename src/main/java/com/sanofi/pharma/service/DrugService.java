package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.request.DrugCreateRequest;
import com.sanofi.pharma.entity.Drug;
import com.sanofi.pharma.entity.Manufacturer;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.exception.BizException;
import com.sanofi.pharma.exception.OptimisticLockException;
import com.sanofi.pharma.mapper.DrugMapper;
import com.sanofi.pharma.mapper.ManufacturerMapper;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.validator.Validator;
import com.sanofi.pharma.service.validation.validator.ValidatorChainBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.sanofi.pharma.enums.ExceptionCode.MANUFACTURER_NOT_EXIST;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DrugService {

    private final DrugMapper drugMapper;

    private final ManufacturerMapper manufacturerMapper;

    private final ValidatorChainBuilder validatorChainBuilder;

    public Drug getById(Long id) {
        return drugMapper.getById(id);
    }

    /**
     * create drug info
     * @return id  drug's id
     */
    public Long createDrug(DrugCreateRequest request) {
        Long manufacturerId = request.getManufacturerId();
        Manufacturer manufacturer = manufacturerMapper.getById(manufacturerId);
        if (manufacturer == null) {
            throw new BizException(MANUFACTURER_NOT_EXIST, manufacturerId);
        }

        Drug drug = convertRequestToEntity(request);
        drugMapper.insert(drug);
        return drug.getId();
    }

    public void deductStockWithVersion(List<PrescriptionItem> items, ValidationContext context) {
        Validator<PrescriptionItem> chain = validatorChainBuilder.buildDrugValidationChain();
        for (PrescriptionItem item : items) {

            chain.validate(item, context);
            if (context.hasFailures()) {
                break;
            }

            //deduct stock
            Drug drug = drugMapper.getById(item.getDrugId());
            int updated = drugMapper.deductStockWithVersion(item.getDrugId(), item.getAmount(), drug.getVersion());

            if (updated == 0) {
                throw new OptimisticLockException("fail to update by version");
            }

            log.debug("deduct drug stock success: drug_id: {}, amount: {}", item.getDrugId(), item.getAmount());
        }
    }

    private Drug convertRequestToEntity(DrugCreateRequest request) {
        Drug drug = new Drug();
        drug.setName(request.getName());
        drug.setBatchNumber(request.getBatchNumber());
        drug.setManufacturerId(request.getManufacturerId());
        drug.setStock(request.getStock());
        drug.setDescription(request.getDescription());
        drug.setExpiryDate(request.getExpiryDate());
        drug.setCreateTime(LocalDateTime.now());
        drug.setUpdateTime(LocalDateTime.now());
        return drug;
    }

}
