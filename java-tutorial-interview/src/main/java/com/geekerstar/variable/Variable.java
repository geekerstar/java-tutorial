package com.geekerstar.variable;

/**
 * @author geekerstar
 * date: 2019/2/1 13:39
 * description:
 */
public class Variable {
    /**
     * 成员变量，类变量
     */
    static int s;
    /**
     * 成员变量，实例变量
     */
    int i;
    /**
     * 成员变量，实例变量
     */
    int j;
    {
        //非静态代码块中的局部变量 i
        int i = 1;
        i++;
        j++;
        s++;
    }
    public void test(int j){//形参，局部变量,j
        j++;
        i++;
        s++;
    }
    public static void main(String[] args) {//形参，局部变量，args
        //局部变量，obj1
        Variable obj1 = new Variable();
        //局部变量，obj1
        Variable obj2 = new Variable();
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}
