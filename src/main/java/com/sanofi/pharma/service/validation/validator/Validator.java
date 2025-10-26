package com.sanofi.pharma.service.validation.validator;


import com.sanofi.pharma.enums.ValidatorType;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.ValidationResult;

public interface Validator<T> {

    /**
     * validate
     */
    ValidationResult validate(T data, ValidationContext context);

    /**
     * next validator
     */
    void setNext(Validator<T> validator);

    /**
     * validate name
     */
    ValidatorType getName();

    /**
     * validate order
     */
    int getOrder();
}
