package spring.boot.rest.api.starter.service.registration.validation;

import org.springframework.stereotype.Component;

import spring.boot.rest.api.starter.common.validation.result.SingleValidationResult;
import spring.boot.rest.api.starter.common.validation.validator.Validator;
import spring.boot.rest.api.starter.service.UserService;

@Component
public class EmailUniquenessValidator implements Validator<String> {

    private final String DEFAULT_MESSAGE = "Email address is already in use";

    private final UserService userService;

    public EmailUniquenessValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SingleValidationResult validate(String email) {
        SingleValidationResult result = SingleValidationResult.valid();
        if (userService.isEmailAlreadyInUse(email)) {
            result.set(DEFAULT_MESSAGE);
        }
        return result;
    }
}