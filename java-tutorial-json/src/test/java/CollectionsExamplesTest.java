import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CollectionsExamplesTest {
    @Test
    void test() {
        Gson gson = new Gson();
        Collection<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        // Serialization
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]
        assertEquals("[1,2,3,4,5]", json);
        // Deserialization
        Type collectionType = new TypeToken<Collection<Integer>>() {}.getType();
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        assertEquals(ints.size(), ints2.size());
        assertTrue(ints.containsAll(ints2));
        // ==> ints2 is same as ints
    }
}

