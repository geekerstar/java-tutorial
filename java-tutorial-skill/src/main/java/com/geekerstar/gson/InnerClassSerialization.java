package com.geekerstar.gson;

import com.google.gson.Gson;


public class InnerClassSerialization {
    public static void main(String[] args) {
        StudentB student = new StudentB();
        student.setRollNo(1);
        StudentB.Name name = student.new Name();

        name.firstName = "Mahesh";
        name.lastName = "Kumar";
        student.setName(name);
        Gson gson = new Gson();

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);
        student = gson.fromJson(jsonString, StudentB.class);

        System.out.println("Roll No: " + student.getRollNo());
        System.out.println("First Name: " + student.getName().firstName);
        System.out.println("Last Name: " + student.getName().lastName);

        String nameString = gson.toJson(name);
        System.out.println(nameString);

        name = gson.fromJson(nameString, StudentB.Name.class);
        System.out.println(name.getClass());
        System.out.println("First Name: " + name.firstName);
        System.out.println("Last Name: " + name.lastName);

        // 静态内部类
        StudentC studentC = new StudentC();
        student.setRollNo(1);
        StudentC.Name nameC = new StudentC.Name();

        nameC.firstName = "Mahesh";
        nameC.lastName = "Kumar";
        studentC.setName(nameC);

        jsonString = gson.toJson(studentC);
        System.out.println(jsonString);
        studentC = gson.fromJson(jsonString, StudentC.class);

        System.out.println("Roll No: " + studentC.getRollNo());
        System.out.println("First Name: " + studentC.getName().firstName);
        System.out.println("Last Name: " + studentC.getName().lastName);
        nameString = gson.toJson(nameC);
        System.out.println(nameString);

        nameC = gson.fromJson(nameString, StudentC.Name.class);
        System.out.println(nameC.getClass());
        System.out.println("First Name: " + nameC.firstName);
        System.out.println("Last Name: " + nameC.lastName);
    }
}

class StudentB {
    private int rollNo;
    private Name name;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    class Name {
        public String firstName;
        public String lastName;
    }
}


class StudentC {
    private int rollNo;
    private Name name;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    static class Name {
        public String firstName;
        public String lastName;
    }
}
