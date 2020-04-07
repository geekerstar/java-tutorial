import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * gson 序列化与反序列化 list 数据
 * https://www.baeldung.com/gson-list
 *
 * @author One
 * @date 2019/09/07
 */
public class ListTest {
    @Test
    void test() {
        List<String> strings = Arrays.asList("1", "2", "3", "4");
        Gson gson = new Gson();
        String jsonString = gson.toJson(strings);
        System.out.println(jsonString);
        List list = gson.fromJson(jsonString, List.class);
        System.out.println(list);
    }

    @Test
    public void givenListOfMyClass_whenSerializing_thenCorrect() {
        List<Person> list = Arrays.asList(new Person(1, "name1"), new Person(2, "name2"));
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        String expectedString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void givenJsonString_whenCorrectDeserializing_() {
        Gson gson = new Gson();
        String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
        Type listOfMyClassObject = new TypeToken<List<Person>>() {
        }.getType();
        List<Person> outputList = gson.fromJson(inputString, listOfMyClassObject);
        int id = outputList.get(0).getId();
        assertEquals(1, id);
        assertEquals("one", outputList.get(0).getName());
    }

    @Test
    public void givenJsonString_whenIncorrectDeserializing_thenThrowClassCastException() {
        Gson gson = new Gson();
        String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
        List<Person> outputList = gson.fromJson(inputString, List.class);
        assertThrows(ClassCastException.class, () -> {
            int id = outputList.get(0).getId();
        });
    }

    @Test
    public void givenJsonString_whenDeserializing_thenReturnListOfPerson() {
        String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
        List<Person> inputList = Arrays.asList(new Person(1, "one"), new Person(2, "two"));
        Type listOfMyClassObject = new TypeToken<List<Person>>() {
        }.getType();
        Gson gson = new Gson();
        List<Person> outputList = gson.fromJson(inputString, listOfMyClassObject);
        assertEquals(inputList, outputList);
    }

    @Test
    public void givenPolymorphicList_whenSerializeWithTypeAdapter_thenCorrect() {
        String expectedString
                = "[{\"petName\":\"Milo\",\"type\":\"Dog\"},{\"breed\":\"Jersey\",\"type\":\"Cow\"}]";
        List<Animal> inList = new ArrayList<>();
        inList.add(new Dog());
        inList.add(new Cow());
        String jsonString = new Gson().toJson(inList);
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void givenPolymorphicList_whenDeserializeWithTypeAdapter_thenCorrect() {
        String inputString
                = "[{\"petName\":\"Milo\",\"type\":\"Dog\"},{\"breed\":\"Jersey\",\"type\":\"Cow\"}]";

        AnimalDeserializer deserializer = new AnimalDeserializer("type");
        deserializer.registerBarnType("Dog", Dog.class);
        deserializer.registerBarnType("Cow", Cow.class);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Animal.class, deserializer)
                .create();

        List<Animal> outList = gson.fromJson(inputString, new TypeToken<List<Animal>>() {
        }.getType());

        assertEquals(2, outList.size());
        assertTrue(outList.get(0) instanceof Dog);
        assertTrue(outList.get(1) instanceof Cow);
    }

}

class AnimalDeserializer implements JsonDeserializer<Animal> {
    private String animalTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Animal>> animalTypeRegistry;

    public AnimalDeserializer(String animalTypeElementName) {
        this.animalTypeElementName = animalTypeElementName;
        this.gson = new Gson();
        this.animalTypeRegistry = new HashMap<>();
    }

    public void registerBarnType(String animalTypeName, Class<? extends Animal> animalType) {
        animalTypeRegistry.put(animalTypeName, animalType);
    }

    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject animalObject = json.getAsJsonObject();
        JsonElement animalTypeElement = animalObject.get(animalTypeElementName);

        Class<? extends Animal> animalType = animalTypeRegistry.get(animalTypeElement.getAsString());
        return gson.fromJson(animalObject, animalType);
    }
}

@Data
class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

abstract class Animal {
    public String type = "Animal";
}

@Data
class Dog extends Animal {
    private String petName;

    public Dog() {
        petName = "Milo";
        type = "Dog";
    }

    // getters and setters
}

@Data
class Cow extends Animal {
    private String breed;

    public Cow() {
        breed = "Jersey";
        type = "Cow";
    }

    // getters and setters
}
