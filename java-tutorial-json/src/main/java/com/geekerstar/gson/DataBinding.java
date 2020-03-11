package com.geekerstar.gson;

import com.google.gson.Gson;

import java.util.Arrays;


public class DataBinding {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String name = "Mahesh Kumar";
        long rollNo = 1;
        boolean verified = false;
        int[] marks = {100, 90, 85};

        //Serialization
        System.out.println("{");
        System.out.println("name: " + gson.toJson(name) + ",");
        System.out.println("rollNo: " + gson.toJson(rollNo) + ",");
        System.out.println("verified: " + gson.toJson(verified) + ",");
        System.out.println("marks:" + gson.toJson(marks));
        System.out.println("}");


        //De-serialization
        name = gson.fromJson("\"Mahesh Kumar\"", String.class);
        rollNo = gson.fromJson("1", Long.class);
        verified = gson.fromJson("false", Boolean.class);
        marks = gson.fromJson("[100,90,85]", int[].class);

        System.out.println("name: " + name);
        System.out.println("rollNo: " + rollNo);
        System.out.println("verified: " + verified);
        System.out.println("marks:" + Arrays.toString(marks));


        // 对象数据绑定
        gson = new Gson();
        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        Student student1 = gson.fromJson(jsonString, Student.class);
        System.out.println(student1);
    }
}
