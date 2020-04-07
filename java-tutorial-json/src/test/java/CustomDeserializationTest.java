import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Gson Deserialization Cookbook
 * https://www.baeldung.com/gson-deserialization-guide
 */
public class CustomDeserializationTest {
    @Test
    public void whenDeserializingToSimpleObject_thenCorrect() {
        String json = "{\"intValue\":1,\"stringValue\":\"one\"}";

        Foo targetObject = new Gson().fromJson(json, Foo.class);

        assertEquals(targetObject.intValue, 1);
        assertEquals(targetObject.stringValue, "one");
    }

    @Test
    public void whenDeserializingToGenericObject_thenCorrect() {
        Type typeToken = new TypeToken<GenericFoo<Integer>>() {
        }.getType();
        String json = "{\"theValue\":1}";

        GenericFoo<Integer> targetObject = new Gson().fromJson(json, typeToken);

        assertEquals(targetObject.theValue, new Integer(1));
    }

    @Test
    public void givenJsonHasExtraValues_whenDeserializing_thenCorrect() {
        String json =
                "{\"intValue\":1,\"stringValue\":\"one\",\"extraString\":\"two\",\"extraFloat\":2.2}";
        Foo targetObject = new Gson().fromJson(json, Foo.class);

        assertEquals(targetObject.intValue, 1);
        assertEquals(targetObject.stringValue, "one");
    }

    @Test
    public void givenJsonHasNonMatchingFields_whenDeserializingWithCustomDeserializer_thenCorrect() {
        String json = "{\"valueInt\":7,\"valueString\":\"seven\"}";

        GsonBuilder gsonBldr = new GsonBuilder();
        gsonBldr.registerTypeAdapter(Foo.class, new FooDeserializerFromJsonWithDifferentFields());
        Foo targetObject = gsonBldr.create().fromJson(json, Foo.class);
        assertEquals(targetObject.intValue, 7);
        assertEquals(targetObject.stringValue, "seven");
    }

    @Test
    public void givenJsonArrayOfFoos_whenDeserializingToArray_thenCorrect() {
        String json = "[{\"intValue\":1,\"stringValue\":\"one\"},{\"intValue\":2,\"stringValue\":\"two\"}]";
        Foo[] targetArray = new GsonBuilder().create().fromJson(json, Foo[].class);
        assertEquals(new Foo(1, "one"), targetArray[0]);
        assertEquals(new Foo(2, "two"), targetArray[1]);
    }

    @Test
    public void givenJsonArrayOfFoos_whenDeserializingCollection_thenCorrect() {
        String json = "[{\"intValue\":1,\"stringValue\":\"one\"},{\"intValue\":2,\"stringValue\":\"two\"}]";
        Type targetClassType = new TypeToken<ArrayList<Foo>>() {
        }.getType();

        Collection<Foo> targetCollection = new Gson().fromJson(json, targetClassType);
        assertTrue(targetCollection instanceof ArrayList);
    }

    @Test
    public void whenDeserializingToNestedObjects_thenCorrect() {
        String json = "{\"intValue\":1,\"stringValue\":\"one\",\"innerFoo\":{\"name\":\"inner\"}}";

        FooWithInner targetObject = new Gson().fromJson(json, FooWithInner.class);
        assertEquals(targetObject.intValue, 1);
        assertEquals(targetObject.stringValue, "one");
        assertEquals(targetObject.innerFoo.name, "inner");
    }

    @Test
    public void whenDeserializingUsingInstanceCreator_thenCorrect() {
        String json = "{\"intValue\":1}";

        GsonBuilder gsonBldr = new GsonBuilder();
        gsonBldr.registerTypeAdapter(Foo.class, new FooInstanceCreator());
        Foo targetObject = gsonBldr.create().fromJson(json, Foo.class);

        assertEquals(targetObject.intValue, 1);
        assertEquals(targetObject.stringValue, "sample");
    }

}

class FooInstanceCreator implements InstanceCreator<Foo> {

    @Override
    public Foo createInstance(Type type) {
        return new Foo("sample");
    }
}


class FooWithInner {
    public int intValue;
    public String stringValue;
    public InnerFoo innerFoo;

    public class InnerFoo {
        public String name;
    }
}

class FooDeserializerFromJsonWithDifferentFields implements JsonDeserializer<Foo> {

    @Override
    public Foo deserialize
            (JsonElement jElement, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jObject = jElement.getAsJsonObject();
        int intValue = jObject.get("valueInt").getAsInt();
        String stringValue = jObject.get("valueString").getAsString();
        return new Foo(intValue, stringValue);
    }
}

class GenericFoo<T> {
    public T theValue;
}

@Data
@AllArgsConstructor
class Foo {
    public int intValue;
    public String stringValue;

    Foo(String stringValue) {
        this.stringValue = stringValue;
    }
    // + standard equals and hashCode implementations
}
