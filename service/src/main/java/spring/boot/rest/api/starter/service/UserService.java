package spring.boot.rest.api.starter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import spring.boot.rest.api.starter.model.User;
import spring.boot.rest.api.starter.persistence.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isEmailAlreadyInUse(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }
}