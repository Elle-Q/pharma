package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.entity.Drug;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.mapper.DrugMapper;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.service.validation.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
@Order(3)
@Component
public class DrugValidator extends AbstractValidator<Object> {

    private final DrugMapper drugMapper;

    @Autowired
    public DrugValidator(DrugMapper drugMapper) {
        super(ValidatorType.DRUG_VALIDATOR, 3);
        this.drugMapper = drugMapper;
    }

    @Override
    protected void doValidate(Object target, ValidationContext context) {
        if (target instanceof PrescriptionDetail) {
            validatePrescriptionDetail((PrescriptionDetail) target, context);
        } else if (target instanceof PrescriptionItem) {
            validatePrescriptionItem((PrescriptionItem) target, context);
        }
    }

    protected void validatePrescriptionDetail(PrescriptionDetail request, ValidationContext context) {
        List<PrescriptionItem> items = request.getItems();
        for (PrescriptionItem item : items) {
            validatePrescriptionItem(item, context);
            if (context.hasFailures()) {
                return;
            }
        }
    }

    protected void validatePrescriptionItem(PrescriptionItem item, ValidationContext context) {
        Drug drug = drugMapper.getById(item.getDrugId());
        if (drug == null) {
            context.addFailureReason(FailureReason.fail(FailureCode.DRUG_NOT_FOUND, item));
            return;
        }
        //check drug stock
        if (drug.getStock() < item.getAmount()) {
            context.addFailureReason(FailureReason.fail(FailureCode.DRUG_OUT_STOCK, drug));
            return;
        }
        if (drug.isExpired()) {
            context.addFailureReason(FailureReason.fail(FailureCode.DRUG_EXPIRED, drug));
        }
    }
}
