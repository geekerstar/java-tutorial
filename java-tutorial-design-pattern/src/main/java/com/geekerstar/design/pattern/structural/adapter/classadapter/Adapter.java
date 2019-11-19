package com.geekerstar.design.pattern.structural.adapter.classadapter;


public class Adapter extends Adaptee implements Target{
    public void request() {
        //...
        super.adapteeRequest();
        //...
    }
}
