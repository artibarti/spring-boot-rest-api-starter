package spring.boot.rest.api.starter.common.validation.validator;

import java.util.ArrayList;
import java.util.List;

import spring.boot.rest.api.starter.common.validation.result.ValidationResult;

public class ValidatorChain<T> implements Validator<T> {

    List<Validator<T>> validators = new ArrayList<>();

    private ValidatorChain(List<Validator<T>> validatorList) {
        validators.addAll(validatorList);
    }

    public static <T> ValidatorChain<T> withValidators(List<Validator<T>> validators) {
        return new ValidatorChain<>(validators);
    }

    public ValidationResult<?> validate(T element) {
        ValidationResult<?> result = null;
        for (Validator<T> validator : validators) {
            result = validator.validate(element);
            if (!result.isValid()) {
                return result;
            }
        }
        return result;
    }
}