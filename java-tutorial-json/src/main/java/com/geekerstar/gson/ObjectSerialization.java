package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ObjectSerialization {
    public static void main(String[] args) throws IOException {
        ObjectSerialization objectSerialization = new ObjectSerialization();
        Student student = new Student();
        student.setAge(10);
        student.setName("haha");
        objectSerialization.writeJSON(student);

        Student student1 = objectSerialization.readJSON();
        System.out.println(student1);
    }

    private void writeJSON(Student student) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter fileWriter = new FileWriter("student.json");
        fileWriter.write(gson.toJson(student));
        fileWriter.close();
    }

    private Student readJSON() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(("student.json")))) {
            Student student = gson.fromJson(bufferedReader, Student.class);
            return student;
        }
    }
}
