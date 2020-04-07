import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author One
 * @date 2019/09/07
 */
public class PrintingTest {
    class SomeObject {
        private String name;
        private BigDecimal value;
        private int age;

        public SomeObject(String name, BigDecimal value, int age) {
            this.name = name;
            this.value = value;
            this.age = age;
        }
    }

    @Test
    void test_pretty_print() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        SomeObject someObject = new SomeObject("one", new BigDecimal("1.0"), 20);
        String jsonOutput = gson.toJson(someObject);
        System.out.println(jsonOutput);
    }

    @Test
    void test_compact_print() {
        Gson gson = new GsonBuilder().create();
        SomeObject someObject = new SomeObject("one", new BigDecimal("1.0"), 20);
        String jsonOutput = gson.toJson(someObject);
        System.out.println(jsonOutput);
    }
}

