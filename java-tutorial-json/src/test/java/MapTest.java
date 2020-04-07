import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Convert JSON to a Map Using Gson
 * https://www.baeldung.com/gson-json-to-map
 *
 * @author One
 * @date 2019/09/07
 */
public class MapTest {
    /**
     * 数值解析都默认为 double，true/false 解析为 boolean，对象解析为 LinkedTreeMaps
     * 一旦key重复，抛出异常 JsonSyntaxException
     */
    @Test
    void tet_map() {
        String jsonString = "{'employee.name':'one','employee.salary':10}";
        Gson gson = new Gson();
        Map map = gson.fromJson(jsonString, HashMap.class);
        assertEquals(2, map.size());
        assertEquals("one", map.get("employee.name"));
        assertEquals(Double.class, map.get("employee.salary").getClass());
    }

    @Test
    void tet_nest_map() {
        String jsonString = "{\"employee.name\":\"one\",\"employee.salary\":10,\"map\": {\"age\": 10}}";
        Gson gson = new Gson();
        Map map = gson.fromJson(jsonString, Map.class);
        assertEquals(3, map.size());
        assertEquals("one", map.get("employee.salary").getClass());
        assertEquals(Double.class, map.get("employee.salary").getClass());
    }

    @Test
    void test_typeToken() {
        String jsonString = "{'Bob' : {'name': 'Bob Willis'},"
                + "'Jenny' : {'name': 'Jenny McCarthy'}, "
                + "'Steve' : {'name': 'Steven Waugh'}}";
        Gson gson = new Gson();
        Map<String, Employee> nameEmployeeMap = gson.fromJson(jsonString, new TypeToken<Map<String, Employee>>() {
        }.getType());
        assertEquals(3, nameEmployeeMap.size());
        assertEquals(Employee.class, nameEmployeeMap.get("Bob").getClass());
        assertEquals("Bob Willis", nameEmployeeMap.get("Bob").getName());
    }

    @Test
    void test_JsonDeserializer() {
        String jsonString = "{'Bob': '2017-06-01', 'Jennie':'2015-01-03'}";
        Type type = new TypeToken<Map<String, Date>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, new StringDateMapDeserializer())
                .create();
        Map<String, Date> empJoiningDateMap = gson.fromJson(jsonString, type);
        assertEquals(2, empJoiningDateMap.size());
        assertEquals(Date.class, empJoiningDateMap.get("Bob").getClass());
        System.out.println(empJoiningDateMap);
    }

    @Data
    private class Employee {
        private String name;
    }

    class StringDateMapDeserializer implements JsonDeserializer<Map<String, Date>> {

        private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                throws JsonParseException {
//            return new DateTime(json.getAsJsonPrimitive().getAsString());
//        }
        @Override
        public Map<String, Date> deserialize(JsonElement elem,
                                             Type type,
                                             JsonDeserializationContext jsonDeserializationContext) {
            return elem.getAsJsonObject()
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().isJsonPrimitive())
                    .filter(e -> e.getValue().getAsJsonPrimitive().isString())
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> formatDate(e.getValue())));
        }

        private Date formatDate(Object value) {
            try {
                return format.parse((value + "").replace("\"", ""));
            } catch (ParseException ex) {
                throw new JsonParseException(ex);
            }
        }
    }
}
