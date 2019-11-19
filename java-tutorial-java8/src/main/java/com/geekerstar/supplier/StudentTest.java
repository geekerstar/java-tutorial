package com.geekerstar.supplier;


import java.util.function.Supplier;

public class StudentTest {

    public static void main(String[] args) {
        Supplier<Student> supplier = Student::new;
        System.out.println(supplier.get().getName());

        System.out.println("-------");

        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getName());
    }
}
