package com.geekerstar.design.pattern.behavioral.mediator;


public class Test {
    public static void main(String[] args) {
        User geely = new User("Geeker");
        User tom= new User("Tom");

        geely.sendMessage(" Hey! Tom! Let's learn Design Pattern");
        tom.sendMessage("OK! Geeker");
    }


}
