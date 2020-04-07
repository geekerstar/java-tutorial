import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Gson Serialization Cookbook
 * https://www.baeldung.com/gson-serialization-guide
 *
 * @author One
 * @date 2019/09/08
 */
public class CustomSerializationTest {
    @Test
    public void givenArrayOfObjects_whenSerializing_thenCorrect() {
        SourceClass[] sourceArray = {new SourceClass(1, "one"), new SourceClass(2, "two")};
        String jsonString = new Gson().toJson(sourceArray);

        String expectedResult = "[{\"intValue\":1,\"stringValue\":\"one\"},{\"intValue\":2,\"stringValue\":\"two\"}]";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void givenCollection_whenSerializing_thenCorrect() {
        Collection<SourceClass> sourceCollection =
                Arrays.asList(new SourceClass(1, "one"), new SourceClass(2, "two"));
        String jsonCollection = new Gson().toJson(sourceCollection);
        String expectedResult = "[{\"intValue\":1,\"stringValue\":\"one\"},{\"intValue\":2,\"stringValue\":\"two\"}]";
        assertEquals(expectedResult, jsonCollection);
    }

    @Test
    void test_dateSerializer() {
        MyObject myObject = new MyObject(new Date(), "one");
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
        String json = gson.toJson(myObject);
        String exceptedJson = "{\"date\":\"2019-09-08\",\"name\":\"one\"}";
        Assertions.assertEquals(exceptedJson, json);
    }

    @Test
    public void givenUsingCustomSerializer_whenChangingNameOfFieldOnSerializing_thenCorrect() {
        SourceClass sourceObject = new SourceClass(7, "seven");
        GsonBuilder gsonBuildr = new GsonBuilder();
        gsonBuildr.registerTypeAdapter(SourceClass.class, new DifferentNameSerializer());
        String jsonString = gsonBuildr.create().toJson(sourceObject);

        //language=JSON
        String expectedResult = "{\"otherIntValue\":7,\"otherStringValue\":\"seven\"}";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void givenIgnoringAField_whenSerializingWithCustomSerializer_thenFieldIgnored() {
        SourceClass sourceObject = new SourceClass(7, "seven");
        GsonBuilder gsonBuildr = new GsonBuilder();
        gsonBuildr.registerTypeAdapter(SourceClass.class, new IgnoringFieldsSerializer());
        String jsonString = gsonBuildr.create().toJson(sourceObject);

        String expectedResult = "{\"intValue\":7}";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void givenUsingCustomDeserializer_whenFieldNotMatchesCriteria_thenIgnored() {
        SourceClass sourceObject = new SourceClass(-1, "minus 1");
        GsonBuilder gsonBuildr = new GsonBuilder();
        gsonBuildr.registerTypeAdapter(SourceClass.class,
                new IgnoringFieldsNotMatchingCriteriaSerializer());
        Gson gson = gsonBuildr.create();
        Type sourceObjectType = new TypeToken<SourceClass>() {
        }.getType();
        String jsonString = gson.toJson(sourceObject, sourceObjectType);

        String expectedResult = "{\"stringValue\":\"minus 1\"}";
        assertEquals(expectedResult, jsonString);
    }
}

class DateSerializer implements JsonSerializer<Date> {
    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateTime.format(src));
    }
}

class IgnoringFieldsNotMatchingCriteriaSerializer
        implements JsonSerializer<SourceClass> {
    @Override
    public JsonElement serialize
            (SourceClass src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jObject = new JsonObject();

        // Criteria: intValue >= 0
        if (src.getIntValue() >= 0) {
            String intValue = "intValue";
            jObject.addProperty(intValue, src.getIntValue());
        }

        String stringValue = "stringValue";
        jObject.addProperty(stringValue, src.getStringValue());

        return jObject;
    }
}

class IgnoringFieldsSerializer implements JsonSerializer<SourceClass> {
    @Override
    public JsonElement serialize
            (SourceClass src, Type typeOfSrc, JsonSerializationContext context) {
        String intValue = "intValue";
        JsonObject jObject = new JsonObject();
        jObject.addProperty(intValue, src.getIntValue());
        return jObject;
    }
}

class DifferentNameSerializer implements JsonSerializer<SourceClass> {
    @Override
    public JsonElement serialize
            (SourceClass src, Type typeOfSrc, JsonSerializationContext context) {
        String otherIntValueName = "otherIntValue";
        String otherStringValueName = "otherStringValue";

        JsonObject jObject = new JsonObject();
        jObject.addProperty(otherIntValueName, src.getIntValue());
        jObject.addProperty(otherStringValueName, src.getStringValue());

        return jObject;
    }
}


@AllArgsConstructor
@Data
class SourceClass {
    private int intValue;
    private String stringValue;

    // standard getters and setters
}

class MyObject {
    private Date date;
    private String name;

    public MyObject(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public MyObject() {
    }
}
