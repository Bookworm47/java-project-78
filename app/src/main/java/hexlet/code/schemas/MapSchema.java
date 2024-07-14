package hexlet.code.schemas;


import java.util.Map;

public class MapSchema<T> extends BaseSchema<Map<String, T>> {

    private int sizeOf = Integer.MAX_VALUE;
    private Map<String, BaseSchema<T>> schemas = null;

    @Override
    public BaseSchema<Map<String, T>> required() {
        this.requiredNotNullOrEmpty = true;
        return this;
    }

    public MapSchema<T> sizeof(int mapSize) {
        this.sizeOf = mapSize;
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> settings) {
        this.schemas = settings;
        return this;
    }

    @Override
    public boolean isValid(Map<String, T> data) {
        valid = super.isValid(data);
        if (sizeOf < Integer.MAX_VALUE && data != null) {
            valid = data.size() == sizeOf;
        }
        if (schemas != null && data != null) {
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                var key = entry.getKey();
                var schema = entry.getValue();
                if (data.containsKey(key)) {
                    valid = schema.isValid(data.get(key));
                    if (!valid) {
                        break;
                    }
                } else {
                    valid = false;
                }
            }
        }
        return valid;
    }
}
