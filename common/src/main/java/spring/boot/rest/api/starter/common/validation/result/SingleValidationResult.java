package spring.boot.rest.api.starter.common.validation.result;

public final class SingleValidationResult implements ValidationResult<String> {

    private String result;

    private SingleValidationResult() {
    }

    public static SingleValidationResult valid() {
        return new SingleValidationResult();
    }

    public void set(String value) {
        this.result = value;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean isValid() {
        return result == null;
    }
}