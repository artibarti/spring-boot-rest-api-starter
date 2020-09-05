package spring.boot.rest.api.starter.service.registration;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.rest.api.starter.common.exception.ValidationException;
import spring.boot.rest.api.starter.common.validation.result.MapValidationResult;
import spring.boot.rest.api.starter.model.User;
import spring.boot.rest.api.starter.model.UserRole;
import spring.boot.rest.api.starter.persistence.repository.UserRepository;
import spring.boot.rest.api.starter.service.registration.form.Registration;
import spring.boot.rest.api.starter.service.registration.validation.RegistrationValidator;

@Service
public class RegistrationService {

    private final RegistrationValidator registrationValidator;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(RegistrationValidator registrationValidator,
                               PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.registrationValidator = registrationValidator;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Registration registration) {
        MapValidationResult validationResult = registrationValidator.validate(registration);
        if (validationResult.isValid()) {
            handleSuccessfulRegistration(registration);
        } else {
            throw new ValidationException("Registration form is invalid", validationResult);
        }
    }

    @Transactional
    private void handleSuccessfulRegistration(Registration registration) {
        User user = createUserFromRegistration(registration);
        userRepository.save(user);
    }

    private User createUserFromRegistration(Registration registration) {
        return new User(registration.email, passwordEncoder.encode(registration.password),
                false, Set.of(UserRole.ROLE_USER));
    }
}