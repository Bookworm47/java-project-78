package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    private Validator validator;
    private Map<String, String> data;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
        data = new HashMap<>();
    }

    @Test
    void testMapSchema() {
        var schema = validator.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(data));

        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.sizeOf(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }
}
