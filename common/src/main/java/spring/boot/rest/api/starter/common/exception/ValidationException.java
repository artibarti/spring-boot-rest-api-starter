package spring.boot.rest.api.starter.common.exception;

import spring.boot.rest.api.starter.common.validation.result.ValidationResult;

public class ValidationException extends RuntimeException {

    private final ValidationResult<?> validationResult;

    public ValidationException(String message, ValidationResult<?> validationResult) {
        super(message);
        this.validationResult = validationResult;
    }

    public ValidationResult<?> getValidationResult() {
        return validationResult;
    }
}