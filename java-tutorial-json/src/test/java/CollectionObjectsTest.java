import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class CollectionObjectsTest {
    class Event {
        private String name;
        private String source;

        private Event(String name, String source) {
            this.name = name;
            this.source = source;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }

    @Test
    void test() {
        ArrayList list = Lists.newArrayList();
        list.add("hello");
        list.add(5);
        list.add(new Event("GREETINGS", "guest"));
        String result = new Gson().toJson(list);
        System.out.println(result);
    }
}
