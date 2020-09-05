package spring.boot.rest.api.starter.web.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.rest.api.starter.service.registration.RegistrationService;
import spring.boot.rest.api.starter.service.registration.form.Registration;

@RestController
@RequestMapping("/public/user/action")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody Registration registration) {
        registrationService.register(registration);
    }
}