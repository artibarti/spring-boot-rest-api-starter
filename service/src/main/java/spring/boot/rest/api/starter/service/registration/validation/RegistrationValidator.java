package spring.boot.rest.api.starter.service.registration.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import spring.boot.rest.api.starter.common.validation.result.MapValidationResult;
import spring.boot.rest.api.starter.common.validation.validator.Validator;
import spring.boot.rest.api.starter.common.validation.validator.ValidatorChain;
import spring.boot.rest.api.starter.service.registration.form.Registration;

@Component
public class RegistrationValidator implements Validator<Registration> {

    private static final String VALID_PASSWORD_REGEX = "(([a-zA-Z0-9.*?-]){6,31})\\d";

    private final ValidatorChain<String> emailValidatorChain;

    private final ValidatorChain<String> passwordValidationChain;

    public RegistrationValidator(EmailUniquenessValidator emailUniquenessValidator) {
        this.emailValidatorChain = ValidatorChain.withValidators(List.of(
                NonEmptyValidator.builder().withMessage("This field is required").build(),
                EmailFormatValidator.builder().withMessage("Email has invalid format").build(),
                emailUniquenessValidator
        ));
        this.passwordValidationChain = ValidatorChain.withValidators(List.of(
                NonEmptyValidator.builder().withMessage("This field is required").build(),
                RegexMatchValidator.builder().withMessage("You need at least seven characters and one number").withPattern(VALID_PASSWORD_REGEX).build()
        ));
    }

    @Override
    public MapValidationResult validate(Registration registration) {
        MapValidationResult result = MapValidationResult.valid();
        result.set("emailError", emailValidatorChain.validate(registration.email));
        result.set("passwordError", passwordValidationChain.validate(registration.password));
        return result;
    }
}