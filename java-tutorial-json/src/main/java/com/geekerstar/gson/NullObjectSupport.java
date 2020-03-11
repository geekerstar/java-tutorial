package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class NullObjectSupport {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Student student = new Student();
        student.setRollNo(1);
        String jsonString = gson.toJson(student);

        System.out.println(jsonString);
        student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);
        System.out.println("===");
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        gson = builder.create();
        student = new Student();
        student.setRollNo(1);
        jsonString = gson.toJson(student);

        System.out.println(jsonString);
        student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);

    }
}
