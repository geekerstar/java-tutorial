package com.geekerstar.gson;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * @author geekerstar
 * @date 2020/3/8 11:40
 * @description
 */
public class ResultTest {


    @Test
    public void test_serialization() {
        // 通常情况下，两种方式创建的 Gson 对象在进行序列化与反序列操作时行为都是一样的，但是第二种方式构建 Gson 对象时，允许进行额外的行为定制，比如格式化 JSON 字符串的输出内容，是否序列化 null 值等等。
        Gson gson = new Gson();
        Result result = new Result(200, "成功", null);
        String json = gson.toJson(result);
        System.out.println("json is " + json);

        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        String buildedJson = buildedGson.toJson(result);
        System.out.println("buildedJson is " + buildedJson);
    }

    @Test
    public void test_jsonObject_serialization() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 400);
        jsonObject.addProperty("message", "参数错误");
        String toJson = gson.toJson(jsonObject);

        String exceptedJson = "{\"code\":400,\"message\":\"参数错误\"}";

        Assert.assertEquals(exceptedJson, toJson);

        JsonObject nestJsonObject = new JsonObject();
        nestJsonObject.addProperty("username", "one");
        nestJsonObject.addProperty("score", 99);
        jsonObject.add("data", nestJsonObject);

        String toJson2 = gson.toJson(jsonObject);

        System.out.println(toJson2);
    }

    @Test
    public void test_deserialization() {
        String json = "{\"code\":400,\"message\":\"参数错误\"}";
        Result result = new Gson().fromJson(json, Result.class);

        Assert.assertEquals(400, result.code);
        Assert.assertEquals("参数错误", result.message);
    }

    @Test
    public void test_genric_object() {
        //language=JSON
        String json = "{\"code\":200,\"message\":\"操作成功\",\"data\":{\"username\": \"one\",\"avater\": \"image.jpg\"" +
                "}}";
        Type type = new TypeToken<Result<User>>() {
        }.getType();
        Result<User> result = new Gson().fromJson(json, type);
        Assert.assertEquals(200, result.code);
        Assert.assertEquals("one", result.data.getUsername());
        Assert.assertEquals("image.jpg", result.data.getAvater());
    }

    @Test
    public void test_resultDeserializer() {
        //language=JSON
        String json = "{\"CODE\": 400,\"MESSAGE\": \"参数错误\"}";
        Gson gson = new GsonBuilder().
//                registerTypeAdapter(Result.class, new ResultDeserializer()).
        create();
        Result result = gson.fromJson(json, Result.class);
        Assert.assertEquals(400, result.code);
        Assert.assertEquals("参数错误", result.message);
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


    /**
     * ==============================================================
     */
    @Data
    class User {
        private String username;
        private String avater;
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
}
