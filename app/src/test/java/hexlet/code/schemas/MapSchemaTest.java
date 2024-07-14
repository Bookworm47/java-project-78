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

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }

    @Test
    void testMapSchema() {
        var schema = validator.<String>map();
        Map<String, String> data = new HashMap<>();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(data));

        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testCheckSchemas() {
        var schema = validator.<String>map();
        Map<String, String> data = new HashMap<>();
        data.put("firstName", "Mike");
        data.put("lastName", "Vazowski");

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().minLength(4));

        schema.shape(schemas);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(data));

        data.put("email", null);
        schemas.put("email", validator.string().required().minLength(5).contains("@gmail.com"));

        assertFalse(schema.isValid(data));

        data.put("email", "mw@gmail.com");

        assertTrue(schema.isValid(data));

        data.put("lastName", "Va");

        assertFalse(schema.isValid(data));
    }
}
