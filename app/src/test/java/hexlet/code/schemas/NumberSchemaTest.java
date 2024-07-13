package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }

    @Test
    void testNumber() {
        var schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(-3));

        schema.positive();

        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(-7));

        schema.range(3, 6);

        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(7));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
    }
}
