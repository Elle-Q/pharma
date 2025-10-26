package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.service.validation.ValidationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * do nothing
 *
 * @author ELLE Q
 * @since 2025-10-25
 */
@Order(100)
@Component
public class NothingValidator extends AbstractValidator<PrescriptionDetail> {


    public NothingValidator() {
        super(ValidatorType.NO_VALIDATOR, 0);
    }

    @Override
    protected void doValidate(PrescriptionDetail request, ValidationContext context) {

    }
}
