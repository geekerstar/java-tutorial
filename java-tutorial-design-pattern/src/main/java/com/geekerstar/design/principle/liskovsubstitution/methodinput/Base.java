package com.geekerstar.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;

/**
 * @author geekerstar
 * date: 2019/1/6 16:40
 * description:
 */
public class Base {
    public void method(HashMap map){
        System.out.println("父类被执行");

    }
}
