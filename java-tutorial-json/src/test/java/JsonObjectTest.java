import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Convert String to JsonObject with Gson
 * https://www.baeldung.com/gson-string-to-jsonobject
 *
 * @author One
 * @date 2019/09/07
 */
public class JsonObjectTest {
    @Test
    void test_parse() {
        String json = "{ \"name\": \"Baeldung\", \"java\": true }";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        assertTrue(jsonObject.isJsonObject());
        assertTrue(jsonObject.get("name").getAsString().equals("Baeldung"));
        assertTrue(jsonObject.get("java").getAsBoolean() == true);
    }

    @Test
    void test_formJson() {
        String json = "{ \"name\": \"Baeldung\", \"java\": true }";
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        assertTrue(convertedObject.isJsonObject());
        assertTrue(convertedObject.get("name").getAsString().equals("Baeldung"));
        assertTrue(convertedObject.get("java").getAsBoolean() == true);
    }

    @Test
    void test() {

    }
}
