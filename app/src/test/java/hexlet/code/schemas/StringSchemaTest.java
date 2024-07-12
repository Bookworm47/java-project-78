package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }

    @Test
    void testString() {
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("True"));

        schema.minLength(5);

        assertTrue(schema.isValid("Hexlet"));
        assertFalse(schema.isValid("Five"));

        schema.minLength(3);

        assertTrue(schema.isValid("Five"));

        schema.contains("good");

        assertTrue(schema.isValid("it's good enough"));
        assertFalse(schema.isValid("such a goooood weather"));
    }

}
