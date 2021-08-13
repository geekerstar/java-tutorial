package com.geekerstar.wx;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author geekerstar
 * @date 2021/8/13 16:52
 * @description https://mp.weixin.qq.com/s/lnXIxzCcy69wCugcFSOg5Q
 */
public class TestThreadLocalRandom {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // Unsafe 设置了构造方法私有，getUnsafe 获取实例方法包私有，在包外只能通过反射获取
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        // Test 类是一个随手写的测试类，只有一个 String 类型的测试类
        Test test = new Test();
        test.value = "12345";
        unsafe.putLong(test, 12L, 2333L);
        System.out.println(test.value);
    }
}

class Test{
    public String value;
}
