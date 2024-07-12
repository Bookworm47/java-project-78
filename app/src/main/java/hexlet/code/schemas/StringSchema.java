package hexlet.code.schemas;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema implements SchemaValidation{

    private boolean required = false;
    private int minLength = 1;
    private String contains = "";
    private boolean valid = false;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public StringSchema contains(String contains) {
        this.contains = contains;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!required && (data == null || data.toString().isEmpty())) {
            valid = true;
        }
        if (data instanceof String) {
            if (required) {
                valid = data.toString().length() >= minLength;
            }
            if (minLength > 1) {
                valid = data.toString().length() >= minLength;
            }
            if (!contains.isEmpty()) {
                valid = data.toString().toLowerCase().contains(contains);
            }
        }
        return valid;
    }
}
