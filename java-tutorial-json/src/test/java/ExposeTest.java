import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author One
 * @date 2019/09/07
 */
public class ExposeTest {
    @Test
    void name() {

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new MyExclusionStrategy(String.class))
                .serializeNulls()
                .create();
        SampleObjectForTest src = new SampleObjectForTest();
        String json = gson.toJson(src);
        System.out.println(json);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface Ignore {
    // Field tag only annotation
}

class SampleObjectForTest {
    private final int annotatedField;
    private final String stringField;
    @Ignore
    private final long longField;

    public SampleObjectForTest() {
        annotatedField = 5;
        stringField = "someDefaultValue";
        longField = 1234;
    }
}

class MyExclusionStrategy implements ExclusionStrategy {
    private final Class<?> typeToSkip;

    protected MyExclusionStrategy(Class<?> typeToSkip) {
        this.typeToSkip = typeToSkip;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return (clazz == typeToSkip);
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Ignore.class) != null;
    }
}

