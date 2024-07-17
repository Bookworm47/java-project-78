package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    abstract BaseSchema<T> required();


    protected final void addCheck(String key, Predicate<T> predicate) {
        checks.put(key, predicate);
    }
    /**
     * Validates the given data.
     *
     * @param data the data to be validated
     * @return true if the data is valid, false otherwise
     */
    public final boolean  isValid(T data) {
        for (Map.Entry<String, Predicate<T>> entry : checks.entrySet()) {
            if (!entry.getValue().test(data)) {
                return false;
            }
        }
        return true;
    }
}
