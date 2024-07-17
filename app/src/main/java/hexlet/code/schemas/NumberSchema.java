package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        Predicate<Integer> predicate = Objects::nonNull;
        addCheck("required", predicate);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> predicate = s -> s == null || s > 0;
        addCheck("positive", predicate);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> predicate = s -> s == null || s >= min && s <= max;
        addCheck("range", predicate);
        return this;
    }

}
