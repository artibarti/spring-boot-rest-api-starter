package spring.boot.rest.api.starter.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spring.boot.rest.api.starter.common.exception.ValidationException;
import spring.boot.rest.api.starter.common.validation.result.ValidationResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ValidationResult<?>> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getValidationResult(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}