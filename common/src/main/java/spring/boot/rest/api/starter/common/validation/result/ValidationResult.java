package spring.boot.rest.api.starter.common.validation.result;

public interface ValidationResult<T> {

    T getResult();

    boolean isValid();
}