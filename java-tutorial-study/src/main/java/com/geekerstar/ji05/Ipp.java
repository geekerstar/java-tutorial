package com.geekerstar.ji05;

public class Ipp {
    //在第一次被载入JVM时运行，但由于是局部变量，x=5不影响后面的值
    static {
        int x = 5;
    }

    static int x, y; //初始化时 x=0,y=0

    public static void main(String[] args) {
        x--;
        System.out.println("步骤一：" + x);  //步骤一：在运行myMethod()；之前，x=-1，开始调用myMethod()函数
        myMethod();  //步骤四：在运行myMethod();之后x=1,y=0
        System.out.println(x + y++ + x);  //步骤五：运行x+(y++)+x=1+0+1=2
    }

    private static void myMethod() {
        y = x++ + ++x;
        System.out.println("步骤二：" + y);  //步骤二：进入myMethod()运行y=(x++) + (++x)后 y=0
        System.out.println("步骤三：" + x);  //步骤三：此时x=1


    }
}
