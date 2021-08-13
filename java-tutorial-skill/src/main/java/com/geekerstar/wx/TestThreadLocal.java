package com.geekerstar.wx;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2021/8/13 18:08
 * @description
 */
public class TestThreadLocal {

    /**
     * https://mp.weixin.qq.com/s/8F1IREeKmpVtvlGNeiK92Q
     */
    @Test
    public void test1(){
        ThreadLocal<String> localName = new ThreadLocal();
        localName.set("张三");
        localName.set("李四");
        String name = localName.get();
        System.out.println(name);
        localName.remove();
        ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("1","1");
        map.put("2","2");
        threadLocal.set(map);
        Map<String, String> stringStringMap = threadLocal.get();
        System.out.println(stringStringMap);
        Map<String, String> stringStringMap1 = threadLocal.get();
        System.out.println(stringStringMap1);
        threadLocal.remove();
    }

    @Test
    public void test2(){
        final ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("hhhh");
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println(threadLocal.get());
            }
        };
        t.start();
    }
}
