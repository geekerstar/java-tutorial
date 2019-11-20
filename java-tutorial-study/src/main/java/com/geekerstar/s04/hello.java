package com.geekerstar.s04;

public class hello {
    static {
        System.out.println("static's hello");
    }

    public static void main(String[] args) {
        System.out.println("main's hello");

    }
}

//静态代码块在main方法前先执行
