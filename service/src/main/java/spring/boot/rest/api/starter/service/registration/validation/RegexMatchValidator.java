package spring.boot.rest.api.starter.service.registration.validation;

import java.util.regex.Pattern;

import spring.boot.rest.api.starter.common.validation.result.SingleValidationResult;
import spring.boot.rest.api.starter.common.validation.validator.Validator;

public class RegexMatchValidator implements Validator<String> {

    private static final String DEFAULT_MESSAGE = "Value has invalid format";

    private final String message;

    private final Pattern pattern;

    private RegexMatchValidator(String message, String pattern) {
        this.message = message;
        this.pattern = Pattern.compile(pattern);
    }

    public static RegexMatchValidatorBuilder builder() {
        return new RegexMatchValidatorBuilder();
    }

    @Override
    public SingleValidationResult validate(String element) {
        SingleValidationResult result = SingleValidationResult.valid();
        if (!pattern.matcher(element).matches()) {
            result.set(message);
        }
        return result;
    }

    public static class RegexMatchValidatorBuilder {

        private String message = DEFAULT_MESSAGE;

        private String pattern = null;

        public RegexMatchValidatorBuilder() {
        }

        public RegexMatchValidatorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public RegexMatchValidatorBuilder withPattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public RegexMatchValidator build() {
            if (pattern == null) {
                throw new RuntimeException("Pattern cannot be null for RegexMatchValidator");
            }
            return new RegexMatchValidator(message, pattern);
        }
    }
}