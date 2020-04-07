import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

/**
 * @author One
 * @date 2019/09/07
 */
public class ObjectExamplesTest {
    class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;

        BagOfPrimitives() {
            // no-args constructor
        }

        @Override
        public String toString() {
            return "BagOfPrimitives{" +
                    "value1=" + value1 +
                    ", value2='" + value2 + '\'' +
                    ", value3=" + value3 +
                    '}';
        }
    }

    @Test
    void name() {
        // Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);
        // ==> json is {"value1":1,"value2":"abc"}
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2);
    }
}
