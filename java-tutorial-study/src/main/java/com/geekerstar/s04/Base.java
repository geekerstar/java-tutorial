package com.geekerstar.s04;

class Base {

    static {
        System.out.println("Base static block");
    }

    {
        System.out.println("Base block");
    }

    public Base(){
        System.out.println("Base constructor");
    }
}

class Derived extends Base{
    static {
        System.out.println("Derived static block");
    }

    {
        System.out.println("Derived block");
    }

    public Derived(){
        System.out.println("Derived constructor");
    }

    public static void main(String[] args) {
        new Derived();
    }
}
