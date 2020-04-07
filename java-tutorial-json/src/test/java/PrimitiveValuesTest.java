import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author One
 * @date 2019/09/07
 */
public class PrimitiveValuesTest {
    @Test
    void test() {
        PrimitiveBundle primitiveBundle = new PrimitiveBundle();
        primitiveBundle.byteValue = (byte) 0x00001111;
        primitiveBundle.shortValue = (short) 3;
        primitiveBundle.intValue = 3;
        primitiveBundle.longValue = 3;
        primitiveBundle.floatValue = 3.5f;
        primitiveBundle.doubleValue = 3.5;
        primitiveBundle.booleanValue = true;
        primitiveBundle.charValue = 'a';

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(primitiveBundle);
        System.out.println(json);
    }

    @Test
    void tet_infinity_value() {
        InfinityValuesExample model = new InfinityValuesExample();
        model.negativeInfinity = Float.NEGATIVE_INFINITY;
        model.positiveInfinity = Float.POSITIVE_INFINITY;
        Gson gson = new Gson();
        assertThrows(IllegalArgumentException.class, () -> gson.toJson(model));
    }

    @Test
    void test_desrializing() {
        Gson gson = new Gson();
        String json = "{\n" +
                "  \"byteValue\": 17,\n" +
                "  \"shortValue\": 3,\n" +
                "  \"intValue\": 3,\n" +
                "  \"longValue\": 3,\n" +
                "  \"floatValue\": 3.5,\n" +
                "  \"doubleValue\": 3.5,\n" +
                "  \"booleanValue\": true,\n" +
                "  \"charValue\": \"a\"\n" +
                "}";
        PrimitiveBundle model = gson.fromJson(json, PrimitiveBundle.class);
        assertEquals(17, model.byteValue);
        assertEquals(3, model.shortValue);
        assertEquals(3, model.intValue);
        assertEquals(3, model.longValue);
        assertEquals(3.5, model.floatValue, 0.0001);
        assertEquals(3.5, model.doubleValue, 0.0001);
        assertTrue(model.booleanValue);
        assertEquals('a', model.charValue);
    }

    @Test
    void test_deserializing_string() {
        String json = "{\"byteValue\": \"15\", \"shortValue\": \"15\", "
                + "\"intValue\": \"15\", \"longValue\": \"15\", \"floatValue\": \"15.0\""
                + ", \"doubleValue\": \"15.0\"}";

        Gson gson = new Gson();
        PrimitiveBundleInitialized model = gson.fromJson(json, PrimitiveBundleInitialized.class);
        System.out.println(model);
    }

    @Test
    void test_deserializing_empty_string() {
        String json = "{\"byteValue\": \"\", \"shortValue\": \"\", "
                + "\"intValue\": \"\", \"longValue\": \"\", \"floatValue\": \"\""
                + ", \"doubleValue\": \"\"}";
        Gson gson = new Gson();
        PrimitiveBundleInitialized primitiveBundleInitialized = gson.fromJson(json, PrimitiveBundleInitialized.class);
        System.out.println(primitiveBundleInitialized);
    }

    @Test
    void test_null_values() {
        String json = "{\"byteValue\": null, \"shortValue\": null, "
                + "\"intValue\": null, \"longValue\": null, \"floatValue\": null"
                + ", \"doubleValue\": null}";
        Gson gson = new GsonBuilder().serializeNulls().create();
        PrimitiveBundleInitialized2 model = gson.fromJson(json, PrimitiveBundleInitialized2.class);
        assertEquals(1, model.byteValue);
        assertEquals(1, model.shortValue);
        assertEquals(1, model.intValue);
        assertEquals(1, model.longValue);
        assertEquals(1, model.floatValue, 0.0001);
        assertEquals(1, model.doubleValue, 0.0001);
    }

    @Test
    void test_overflow_values() {
        String json = "{\"value\": 300}";
        ByteExample byteExample = new Gson().fromJson(json, ByteExample.class);
        assertEquals(44, byteExample.value);
    }

    @Test
    void tet_unicode() {
        String unicode = "{\"value\": \"\\u00AE\"}";
        System.out.println(new JsonParser().parse(unicode).getAsJsonObject().getAsJsonPrimitive("value"));
    }
}

class ByteExample {
    public byte value;
}

@Data
class PrimitiveBundleInitialized2 {
    public byte byteValue = (byte) 1;
    public short shortValue = (short) 1;
    public int intValue = 1;
    public long longValue = 1L;
    public float floatValue = 1.0f;
    public double doubleValue = 1;
}

class PrimitiveBundleInitialized {
    public String byteValue;
    public String shortValue;
    public String intValue;
    public String longValue;
    public String floatValue;
    public String doubleValue;

    @Override
    public String toString() {
        return "PrimitiveBundleInitialized{" +
                "byteValue='" + byteValue + '\'' +
                ", shortValue='" + shortValue + '\'' +
                ", intValue='" + intValue + '\'' +
                ", longValue='" + longValue + '\'' +
                ", floatValue='" + floatValue + '\'' +
                ", doubleValue='" + doubleValue + '\'' +
                '}';
    }
}

class InfinityValuesExample {
    public float negativeInfinity;
    public float positiveInfinity;
}

@Data
class PrimitiveBundle {
    public byte byteValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
    public boolean booleanValue;
    public char charValue;
}
