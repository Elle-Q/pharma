package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.ValidationResult;

/**
 * @author ELLE Q
 * @since 2025-10-25
 */
public abstract class AbstractValidator<T> implements Validator<T> {
    protected Validator<T> next;

    protected ValidatorType validatorType;
    protected int order;

    public AbstractValidator(ValidatorType validatorType, int order) {
        this.validatorType = validatorType;
        this.order = order;
    }

    protected abstract void doValidate(T data, ValidationContext context);

    @Override
    public ValidationResult validate(T data, ValidationContext context) {
        try {
            //do current validator
            doValidate(data, context);

            //if current validator has failures, return
            if (context.hasFailures()) {
                return context.getValidationResult();
            }

            //else proceed to the next validator
            return validateNext(data, context);

        } catch (Exception e) {
            //record validator fail
            context.addFailureReason(FailureReason.fail0(FailureCode.VALIDATOR_FAIL, getName().name(), e.getMessage()));
            e.printStackTrace();
            return context.getValidationResult();
        }
    }

    private ValidationResult validateNext(T data, ValidationContext context) {
        if (next == null) {
            return context.getValidationResult();
        }
        return next.validate(data, context);
    }

    @Override
    public void setNext(Validator<T> validator) {
        this.next = validator;
    }

    @Override
    public ValidatorType getName() {
        return validatorType;
    }

    @Override
    public int getOrder() {
        return order;
    }

}
