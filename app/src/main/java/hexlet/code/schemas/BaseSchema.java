package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    protected boolean requiredNotNullOrEmpty = false;
    protected boolean valid = true;

    abstract BaseSchema required();

    /**
     * Validates the given data.
     *
     * @param data the data to be validated
     * @return true if the data is valid, false otherwise
     */
    boolean  isValid(T data) {
        if (requiredNotNullOrEmpty) {
            valid = data != null;
        }
        return valid;
    }
}
