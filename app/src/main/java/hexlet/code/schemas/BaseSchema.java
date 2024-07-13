package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    boolean requiredNotNullOrEmpty = false;
    boolean valid = true;

    abstract BaseSchema<T> required();

    boolean  isValid(T data) {
        if (requiredNotNullOrEmpty) {
            valid = data != null;
        }
        return valid;
    }
}
