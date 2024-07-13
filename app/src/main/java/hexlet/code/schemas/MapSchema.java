package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {

    private int sizeOf = Integer.MAX_VALUE;

    @Override
    BaseSchema<Map<String, String>> required() {
        this.requiredNotNullOrEmpty = true;
        return this;
    }

    public MapSchema sizeOf(int sizeOf) {
        this.sizeOf = sizeOf;
        return this;
    }

    @Override
    boolean isValid(Map<String, String> data) {
        valid = super.isValid(data);
        if (requiredNotNullOrEmpty) {
            valid = data != null;
        }
        if (sizeOf < Integer.MAX_VALUE) {
            valid = data.size() == sizeOf;
        }
        return valid;
    }
}
