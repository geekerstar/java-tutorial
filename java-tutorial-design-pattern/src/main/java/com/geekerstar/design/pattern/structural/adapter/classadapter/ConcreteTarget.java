package com.geekerstar.design.pattern.structural.adapter.classadapter;


public class ConcreteTarget implements Target {
    public void request() {
        System.out.println("concreteTarget目标方法");
    }

}
