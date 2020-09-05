package spring.boot.rest.api.starter.common.validation.result;

import java.util.HashMap;
import java.util.Map;

public final class MapValidationResult implements ValidationResult<Map<String, Object>> {

    private Map<String, Object> result = new HashMap<>();

    private MapValidationResult() {
    }

    private MapValidationResult(Map<String, Object> map) {
        this.result = map;
    }

    public static MapValidationResult valid() {
        return new MapValidationResult();
    }

    public void set(String key, ValidationResult<?> validationResult) {
        result.put(key, validationResult.getResult());
    }

    public Map<String, Object> getResult() {
        return result;
    }

    @Override
    public boolean isValid() {
        return result.values().stream().allMatch(this::isEntryValid);
    }

    private boolean isEntryValid(Object o) {
        if (o instanceof Map) {
            return ((Map<?, ?>) o).values().stream().allMatch(this::isEntryValid);
        } else if (o instanceof String) {
            return false;
        } else if (o == null) {
            return true;
        } else {
            throw new RuntimeException("Illegal validation result type");
        }
    }
}