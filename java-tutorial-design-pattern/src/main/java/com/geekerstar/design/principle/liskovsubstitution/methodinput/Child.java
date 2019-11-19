package com.geekerstar.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * date: 2019/1/6 16:41
 * description:
 */
public class Child extends Base {
    @Override
    public void method(HashMap map) {
        System.out.println("子类HashMap入参方法被执行");

    }

    public void method(Map map){
        System.out.println("子类Map入参方法被执行");

    }
}
