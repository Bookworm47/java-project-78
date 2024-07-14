package hexlet.code.schemas;


import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    private int sizeOf = Integer.MAX_VALUE;
    private Map<String, BaseSchema<String>> schemas = null;

    @Override
    public BaseSchema<Map<String, String>> required() {
        this.requiredNotNullOrEmpty = true;
        return this;
    }

    public MapSchema sizeof(int mapSize) {
        this.sizeOf = mapSize;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> settings) {
        this.schemas = settings;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> data) {
        valid = super.isValid(data);
        if (sizeOf < Integer.MAX_VALUE && data != null) {
            valid = data.size() == sizeOf;
        }
        if (schemas != null && data != null) {
            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                var key = entry.getKey();
                var schema = entry.getValue();
                if (data.containsKey(key)) {
                    valid = schema.isValid(data.get(key));
                    if (!valid) {
                        break;
                    }
                }
            }
        }
        return valid;
    }
}
