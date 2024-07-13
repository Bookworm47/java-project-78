package hexlet.code.schemas;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    private int minLength = 0;
    private String contains = "";

    public StringSchema required() {
        this.requiredNotNullOrEmpty = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(String data) {
        valid = super.isValid(data);
        if (data != null) {
            if (requiredNotNullOrEmpty) {
                valid = data.length() > minLength;
            }
            if (minLength > 0) {
                valid = data.length() >= minLength;
            }
            if (!contains.isEmpty()) {
                valid = data.toLowerCase().contains(contains);
            }
        }
        return valid;
    }
}

