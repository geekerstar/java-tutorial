package com.geekerstar.design.pattern.structural.adapter.objectadapter;


public class Adapter implements Target {
    private Adaptee adaptee = new Adaptee();

    public void request() {
        //...
        adaptee.adapteeRequest();
        //...
    }
}
