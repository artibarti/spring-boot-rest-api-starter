package spring.boot.rest.api.starter.service.registration.validation;

import org.apache.commons.lang3.StringUtils;

import spring.boot.rest.api.starter.common.validation.result.SingleValidationResult;
import spring.boot.rest.api.starter.common.validation.validator.Validator;

public class NonEmptyValidator implements Validator<String> {

    private static final String DEFAULT_MESSAGE = "This field is required";

    private String message;

    private NonEmptyValidator(String message) {
        this.message = message;
    }

    public static NonEmptyValidatorBuilder builder() {
        return new NonEmptyValidatorBuilder();
    }

    @Override
    public SingleValidationResult validate(String element) {
        SingleValidationResult result = SingleValidationResult.valid();
        if (StringUtils.isBlank(element)) {
            result.set(message);
        }
        return result;
    }

    public static class NonEmptyValidatorBuilder {

        private String message = DEFAULT_MESSAGE;

        public NonEmptyValidatorBuilder() {
        }

        public NonEmptyValidatorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public NonEmptyValidator build() {
            return new NonEmptyValidator(message);
        }
    }
}