package com.geekerstar.other;


import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        Predicate<String> predicate = p -> p.length() > 5;

        System.out.println(predicate.test("hello1"));
    }
}
