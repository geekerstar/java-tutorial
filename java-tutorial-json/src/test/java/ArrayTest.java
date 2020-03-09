import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArrayTest {
    @Test
    void test_array() {
        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
        String s = gson.toJson(ints);// ==> [1,2,3,4,5]
        assertEquals("[1,2,3,4,5]", s);

        String s1 = gson.toJson(strings);// ==> ["abc", "def", "ghi"]
        assertEquals("[\"abc\",\"def\",\"ghi\"]", s1);
        String[] strings1 = gson.fromJson(s1, String[].class);
        assertEquals(strings.length, strings1.length);
        assertEquals(strings[0], strings1[0]);

        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        assertEquals(1, ints2[0]);
        assertEquals(5, ints2[4]);
    }

    @Test
    void name() {
        String inputString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";
        MyClass[] myClasses = new Gson().fromJson(inputString, MyClass[].class);
        System.out.println(Arrays.toString(myClasses));
    }

    @Data
    class MyClass {
        private int id;
        private String name;

        public MyClass(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}


