package com.geekerstar.gson;

import com.google.gson.*;


public class TreeModel {
    private static String jsonString =
            "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";

    public static void main(String[] args) {
        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(jsonString);
        System.out.println(rootNode);
        //{"name":"Mahesh Kumar","age":21,"verified":false,"marks":[100,90,85]}

        JsonObject details = rootNode.getAsJsonObject();
        JsonElement nameNode = details.get("name");
        System.out.println("name:" + nameNode.getAsString());

        JsonElement ageNode = details.get("age");
        System.out.println("Age: " + ageNode.getAsInt());

        if (rootNode.isJsonObject()) {
            details = rootNode.getAsJsonObject();
            nameNode = details.get("name");
            System.out.println("Name: " + nameNode.getAsString());

            ageNode = details.get("age");
            System.out.println("Age: " + ageNode.getAsInt());

            JsonElement verifiedNode = details.get("verified");
            System.out.println("Verified: " + (verifiedNode.getAsBoolean() ? "Yes" : "No"));
            JsonArray marks = details.getAsJsonArray("marks");

            for (int i = 0; i < marks.size(); i++) {
                JsonPrimitive value = marks.get(i).getAsJsonPrimitive();
                System.out.print(value.getAsInt() + " ");
            }
        }
    }
}
