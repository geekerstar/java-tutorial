import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

/**
 * @author One
 * @date 2019/09/07
 */
public class SerializingandDeserializingGenericTypesTest {
    class Foo<T> {
        T value;

        @Override
        public String toString() {
            return "Foo{" +
                    "value=" + value +
                    '}';
        }

        public void setBar(T bar) {
            this.value = bar;
        }
    }

    class Bar {
        @Override
        public String toString() {
            return "Bar{}";
        }
    }

    @Test
    void test() {
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        foo.setBar(new Bar());
        System.out.println(foo);
        Type fooType = new TypeToken<Foo<Bar>>() {
        }.getType();
        String json = gson.toJson(foo, fooType);// May not serialize foo.value correctly
        System.out.println(json);
        Foo foo1 = gson.fromJson(json, fooType);// Fails to deserialize foo.value as Bar
        System.out.println(foo1);
    }
}
