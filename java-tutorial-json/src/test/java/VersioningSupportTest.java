import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import org.junit.jupiter.api.Test;

/**
 * @author One
 * @date 2019/09/07
 */
public class VersioningSupportTest {
    @Test
    void test() {
        VersionedClass versionedObject = new VersionedClass();
        Gson gson = new GsonBuilder().setVersion(1.0).create();
        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
        gson = new Gson();
        jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
    }
}

class VersionedClass {
    @Since(1.1)
    private final String newerField;
    @Since(1.0)
    private final String newField;
    private final String field;

    public VersionedClass() {
        this.newerField = "newer";
        this.newField = "new";
        this.field = "old";
    }
}
