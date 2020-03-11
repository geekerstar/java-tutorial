package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;


public class VersionSupport {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setVersion(1.0);
        Gson gson = builder.create();

        StudentD student = new StudentD();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        gson = new Gson();
        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }

}

class StudentD {
    @Since(1.0)
    private int rollNo;

    @Since(1.0)
    private String name;

    @Since(1.1)
    private boolean verified;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "StudentD{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", verified=" + verified +
                '}';
    }
}
