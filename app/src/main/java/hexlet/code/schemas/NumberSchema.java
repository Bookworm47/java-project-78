package hexlet.code.schemas;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive = false;
    private int[] range = null;

    @Override
    public NumberSchema required() {
        this.requiredNotNullOrEmpty = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.range = new int[]{min, max};
        return this;
    }

    @Override
    public boolean isValid(Integer data) {
        valid = super.isValid(data);
        if (requiredNotNullOrEmpty) {
            valid = data != null;
        }
        if (positive) {
            valid = super.isValid(data);
            if (data != null) {
                valid = data > 0;
            }
        }
        if (range != null) {
            valid = super.isValid(data);
            if (data != null) {
                int min = range[0];
                int max = range[1];
                valid = data >= min && data <= max;
            }
        }

        return valid;
    }
}
