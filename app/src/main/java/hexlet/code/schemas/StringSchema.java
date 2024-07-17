package hexlet.code.schemas;


import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@NoArgsConstructor
public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> predicate = s -> s != null && !s.isEmpty();
        addCheck("required", predicate);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> predicate = s -> s.length() >= length;
        addCheck("minLength", predicate);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> predicate = s -> s.contains(substring);
        addCheck("contains", predicate);
        return this;
    }

}

