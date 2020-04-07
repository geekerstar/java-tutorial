import com.google.gson.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

public class CustomSerializerTest {
    @Test
    void test() {
        GsonBuilder gson = new GsonBuilder();
        // 修改序列化行为
        gson.registerTypeAdapter(DateTime.class, new DateTimeSerializer());
        DateTime dateTime = new DateTime("yyyy-dd");
        System.out.println(gson.create().toJson(dateTime));
    }
}

class DateTime {
    private final String value;

    public DateTime(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        //language=JSON
        return "{" +
                "\"dateTime\":" + value + "}";
    }
}

class DateTimeSerializer implements JsonSerializer<DateTime> {
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}

class DateTimeDeserializer implements JsonDeserializer<DateTime> {
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new DateTime(json.getAsJsonPrimitive().getAsString());
    }
}
