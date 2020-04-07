import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author One
 * @date 2019/09/07
 */
public class NullTest {
    class SomeObject {
        private String name;
        private int age;
        private BigDecimal balance;

        public SomeObject(String name, int age, BigDecimal balance) {
            this.name = name;
            this.age = age;
            this.balance = balance;
        }
    }

    @Test
    void test_null() {
        Gson gson = new Gson();
        String s = gson.toJson(new SomeObject(null, 0, null));
        System.out.println(s);
    }

    @Test
    void test() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String s = gson.toJson(new SomeObject(null, 0, null));
        System.out.println(s);
    }
}
