package com.geekerstar.defalutmethod;


public interface MyInterface2 {

    default void myMethod() {
        System.out.println("MyInterface2");
    }
}
