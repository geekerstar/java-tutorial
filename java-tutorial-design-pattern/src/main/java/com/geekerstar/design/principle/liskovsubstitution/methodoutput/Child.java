package com.geekerstar.design.principle.liskovsubstitution.methodoutput;

import java.util.HashMap;

/**
 * @author geekerstar
 * date: 2019/1/6 16:47
 * description:
 */
public class Child extends Base {
    @Override
    public HashMap method() {
        HashMap hashMap = new HashMap();
        System.out.println("子类Method被执行");
        hashMap.put("message","子类Method被执行");
        return hashMap;
    }
}
