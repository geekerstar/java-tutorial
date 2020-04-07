import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Save Data to a JSON File with Gson
 * https://www.baeldung.com/gson-save-file
 *
 * @author One
 * @date 2019/09/07
 */
public class JSONFileTest {
    /**
     * 输出到指定文件
     *
     * @throws IOException
     */
    @Test
    void test_json_file() throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        User user = new User(1, null, "American");
        String filePath = "/Users/One/Nutstore Files/One/OpenSource/gson-actions/src/test/resources/file.json";
        System.out.println(gson.toJson(user));
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(user, fileWriter);
        fileWriter.close(); // 必须手动关闭，否则不会有内容
    }

    @Test
    void test_collection_json_file() throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        User[] users = new User[]{new User(1, "Mike"), new User(2, "Tom")};
        String filePath = "/Users/One/Nutstore Files/One/OpenSource/gson-actions/src/test/resources/collection.json";
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(users, fileWriter);
        fileWriter.close(); // 必须手动关闭，否则不会有内容
    }
}

@Data
class User {
    private int id;
    private String name;
    private transient String nationality;

    public User(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public User(int id, String name) {
        this(id, name, null);
    }
}
