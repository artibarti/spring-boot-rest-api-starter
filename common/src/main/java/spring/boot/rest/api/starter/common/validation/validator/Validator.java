package spring.boot.rest.api.starter.common.validation.validator;

import spring.boot.rest.api.starter.common.validation.result.ValidationResult;

public interface Validator<T> {

    ValidationResult<?> validate(T element);
}