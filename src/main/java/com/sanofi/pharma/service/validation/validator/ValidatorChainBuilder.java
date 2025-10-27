package com.sanofi.pharma.service.validation.validator;

import com.sanofi.pharma.enums.ValidatorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * build validation chain
 *
 * @author ELLE Q
 * @since 2025-10-26
 */
@RequiredArgsConstructor
@Component
public class ValidatorChainBuilder {

    private final List<Validator<?>> allValidators;

    public <T> Validator<T> buildValidationChain() {
        return buildChain(allValidators);
    }

    public <T> Validator<T> buildDrugValidationChain() {
        List<Validator<?>> validators = allValidators.stream()
                .filter(validator -> validator.getName() == ValidatorType.DRUG_VALIDATOR)
                .collect(Collectors.toList());

        return buildChain(validators);
    }

    public <T> Validator<T> buildContractValidationChain() {
        List<Validator<?>> validators = allValidators.stream()
                .filter(validator -> validator.getName() == ValidatorType.CONTRACT_VALIDATOR)
                .collect(Collectors.toList());

        return buildChain(validators);
    }

    public <T> Validator<T> buildPrescriptionValidationChain() {
        List<Validator<?>> validators = allValidators.stream()
                .filter(validator -> validator.getName() == ValidatorType.PRESCRIPTION_VALIDATOR)
                .collect(Collectors.toList());

        return buildChain(validators);
    }

    private <T> Validator<T> buildChain(List<Validator<?>> validators) {
        if (validators.isEmpty()) {
            return null;
        }
        //sort by order
        validators.sort(Comparator.comparingInt(Validator::getOrder));

        //build chain by order
        Validator<T> head = (Validator<T>) validators.get(0);
        Validator<T> cur = head;
        for (int i = 1; i < validators.size(); i++) {
            Validator<T> next = (Validator<T>) validators.get(i);
            cur.setNext(next);
            cur = next;
        }
        return head;
    }
}
