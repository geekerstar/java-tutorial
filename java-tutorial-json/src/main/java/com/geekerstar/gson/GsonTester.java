package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonTester {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);
        // Student [ name: Mahesh, age: 21 ]

        jsonString = gson.toJson(student);
        System.out.println(jsonString);
        /**
         * {
         *   "name": "Mahesh",
         *   "age": 21
         * }
         */

    }

}


