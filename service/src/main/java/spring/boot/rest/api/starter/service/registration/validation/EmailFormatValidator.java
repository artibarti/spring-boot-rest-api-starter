package spring.boot.rest.api.starter.service.registration.validation;

import spring.boot.rest.api.starter.common.validation.result.SingleValidationResult;
import spring.boot.rest.api.starter.common.validation.validator.Validator;

public class EmailFormatValidator implements Validator<String> {

    private static final String DEFAULT_MESSAGE = "Email has invalid format";

    private final String message;

    private EmailFormatValidator(String message) {
        this.message = message;
    }

    public static EmailFormatValidatorBuilder builder() {
        return new EmailFormatValidatorBuilder();
    }

    @Override
    public SingleValidationResult validate(String email) {
        SingleValidationResult result = SingleValidationResult.valid();
        if (!org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email)) {
            result.set(message);
        }
        return result;
    }

    public static class EmailFormatValidatorBuilder {

        private String message = DEFAULT_MESSAGE;

        public EmailFormatValidatorBuilder() {
        }

        public EmailFormatValidatorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public EmailFormatValidator build() {
            return new EmailFormatValidator(message);
        }
    }
}