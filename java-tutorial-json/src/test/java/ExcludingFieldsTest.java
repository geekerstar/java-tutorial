import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

/**
 * @author One
 * @date 2019/09/07
 */
public class ExcludingFieldsTest {
    @Test
    void test() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
    }
}
