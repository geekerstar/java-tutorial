package com.geekerstar.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;

/**
 * @author geekerstar
 * date: 2019/1/6 16:43
 * description:
 */
public class Test {
    public static void main(String[] args) {
        Child child = new Child();
        HashMap hashMap = new HashMap();
        child.method(hashMap);
    }
}
