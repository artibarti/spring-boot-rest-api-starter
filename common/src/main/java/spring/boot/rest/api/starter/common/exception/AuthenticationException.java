package spring.boot.rest.api.starter.common.exception;

public class AuthenticationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Authentication failed";

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super(DEFAULT_MESSAGE);
    }
}