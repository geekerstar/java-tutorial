import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

/**
 * @author One
 * @date 2019/09/08
 */
public class ResultTest {
    @Test
    void test_serialization() {
        Gson gson = new Gson();
        Result result = new Result(200, "成功", null);
        String json = gson.toJson(result);
        System.out.println("json is " + json);
        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        String buildedJson = buildedGson.toJson(result);
        System.out.println("buildedJson is " + buildedJson);
    }

    @Test
    void test_jsonObject_serialization() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 400);
        jsonObject.addProperty("message", "参数错误");
        String toJson = gson.toJson(jsonObject);
        String exceptedJson = "{\"code\":400,\"message\":\"参数错误\"}";
        Assertions.assertEquals(exceptedJson, toJson);

        JsonObject nestJsonObject = new JsonObject();
        nestJsonObject.addProperty("username", "one");
        nestJsonObject.addProperty("score", 99);
        jsonObject.add("data", nestJsonObject);
        String toJson2 = gson.toJson(jsonObject);
        System.out.println(toJson2);
    }

    @Test
    void test_deserialization() {
        String json = "{\"code\":400,\"message\":\"参数错误\"}";
        Result result = new Gson().fromJson(json, Result.class);
        Assertions.assertEquals(400, result.code);
        Assertions.assertEquals("参数错误", result.message);
    }

    @Test
    void test_genric_object() {
        //language=JSON
        String json = "{\"code\":200,\"message\":\"操作成功\",\"data\":{\"username\": \"one\",\"avater\": \"image.jpg\"" +
                "}}";
        Type type = new TypeToken<Result<User>>() {
        }.getType();
        Result<User> result = new Gson().fromJson(json, type);
        Assertions.assertEquals(200, result.code);
        Assertions.assertEquals("one", result.data.getUsername());
        Assertions.assertEquals("image.jpg", result.data.getAvater());
    }

    class User {
        private String username;
        private String avater;

        public String getUsername() {
            return username;
        }

        public String getAvater() {
            return avater;
        }
    }

    @JsonAdapter(ResultDeserializer.class)
    class Result<T> {
        private int code;
        private String message;
        private T data;

        public Result(int code, String message, T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    @Test
    void test_resultDeserializer() {
        //language=JSON
        String json = "{\"CODE\": 400,\"MESSAGE\": \"参数错误\"}";
        Gson gson = new GsonBuilder().
//                registerTypeAdapter(Result.class, new ResultDeserializer()).
        create();
        Result result = gson.fromJson(json, Result.class);
        Assertions.assertEquals(400, result.code);
        Assertions.assertEquals("参数错误", result.message);
    }

    class ResultDeserializer implements JsonDeserializer<Result> {
        @Override
        public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Result<Object> result = new Result<>(object.getAsJsonPrimitive("CODE").getAsInt(),
                    object.getAsJsonPrimitive("MESSAGE").getAsString(), null);
            return result;
        }
    }
}

