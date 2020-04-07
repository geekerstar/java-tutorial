import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.Test;

/**
 * @author One
 * @date 2019/09/07
 */
public class JSONFieldNamingSupportTest {
    private class SomeObject {
        @SerializedName("custom_naming")
        private final String someField;
        private final String someOtherField;

        public SomeObject(String a, String b) {
            this.someField = a;
            this.someOtherField = b;
        }

        @Override
        public String toString() {
            return "SomeObject{" +
                    "someField='" + someField + '\'' +
                    ", someOtherField='" + someOtherField + '\'' +
                    '}';
        }
    }

    @Test
    void test() {
        SomeObject someObject = new SomeObject("first", "second");
        Gson gson = new GsonBuilder().create();
        String jsonRepresentation = gson.toJson(someObject);
        System.out.println(jsonRepresentation);
        SomeObject someObject1 = gson.fromJson(jsonRepresentation, SomeObject.class);
        System.out.println(someObject1);
    }
}
