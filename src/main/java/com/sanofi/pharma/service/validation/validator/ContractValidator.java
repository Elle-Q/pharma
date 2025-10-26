package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.dto.common.ContractDrug;
import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.mapper.ContractMapper;
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
@Order(1)
@Component
public class ContractValidator extends AbstractValidator<Object> {

    private final ContractMapper contractMapper;

    @Autowired
    public ContractValidator(ContractMapper contractMapper) {
        super(ValidatorType.CONTRACT_VALIDATOR, 2);
        this.contractMapper = contractMapper;
    }

    @Override
    protected void doValidate(Object target, ValidationContext context) {
        if (target instanceof PrescriptionDetail) {
            validatePrescriptionDetail((PrescriptionDetail) target, context);
        } else if (target instanceof PrescriptionItem) {
            validatePrescriptionItem((PrescriptionItem) target, context);
        }
    }

    private void validatePrescriptionDetail(PrescriptionDetail request, ValidationContext context) {
        List<PrescriptionItem> items = request.getItems();
        for (PrescriptionItem item : items) {
            validatePrescriptionItem(item, context);
            if (context.hasFailures()) {
                return;
            }
        }
    }

    private void validatePrescriptionItem(PrescriptionItem item, ValidationContext context) {

        //check if pharmacy is contracted for this drug
        ContractDrug contractDrug = contractMapper.findDetailByPharmacyIdAndDrugId(item.getPharmacyId(), item.getDrugId());
        if (contractDrug == null) {
            context.addFailureReason(FailureReason.fail(FailureCode.DRUG_NOT_CONTRACTED, item));
            return;
        }

        //check drug allocated amount is sufficient
        int amountRequest = item.getAmount();
        if (amountRequest > contractDrug.getRemainingAmout()) {
            context.addFailureReason(FailureReason.fail(FailureCode.EXCEEDS_ALLOCATION_LIMIT, contractDrug, item));
        }
    }
}
