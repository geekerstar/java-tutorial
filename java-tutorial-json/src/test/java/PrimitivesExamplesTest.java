import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * one on 2019/9/7.
 */
class PrimitivesExamplesTest {
    @Test
    void test() {
        // Serialization
        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(new Long(10)); // ==> 10
        int[] values = {1};
        gson.toJson(values);       // ==> [1]

        // Deserialization
        int one = gson.fromJson("1", int.class);
        Integer oneInteger = gson.fromJson("1", Integer.class);
        assertTrue(oneInteger instanceof Integer);
        Long oneLong = gson.fromJson("1", Long.class);
        assertTrue(oneLong instanceof Long);
        Boolean falseValue = gson.fromJson("false", Boolean.class);
        assertTrue(falseValue instanceof Boolean);
        String str = gson.fromJson("\"abc\"", String.class);
        assertEquals("abc", str);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
        assertEquals("abc", anotherStr[0]);
    }
}
