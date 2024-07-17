package hexlet.code.schemas;


import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public BaseSchema<Map<String, String>> required() {
        Predicate<Map<String, String>> predicate = Objects::nonNull;
        addCheck("required", predicate);
        return this;
    }

    public MapSchema sizeof(int mapSize) {
        Predicate<Map<String, String>> predicate = s -> s.size() == mapSize;
        addCheck("sizeOf", predicate);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> settings) {
        Predicate<Map<String, String>> predicate = s -> {
            for (Map.Entry<String, BaseSchema<String>> entry : settings.entrySet()) {
                var schema = entry.getValue();
                var key = entry.getKey();
                if (s == null) {
                    return true;
                } else if (s.containsKey(key) && !schema.isValid(s.get(key))) {
                    return false;
                }
            }
            return true;
        };
        addCheck("shape", predicate);
        return this;
    }

}
