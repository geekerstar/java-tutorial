package com.geekerstar.defalutmethod;


public interface MyInterface1 {

    default void myMethod() {
        System.out.println("MyInterface1");
    }
}
