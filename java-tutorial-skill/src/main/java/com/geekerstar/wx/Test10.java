package com.geekerstar.wx;

/**
 * @author geekerstar
 * @date 2021/7/26 10:32
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247485964&idx=1&sn=207128ada43c534037aee77f544698c4&chksm=eb538f3adc24062c224dbdc02fac474b4ca6c4d6072a2cae8daefeda02129d46382da2ebca21&mpshare=1&scene=1&srcid=1028VLmzsVH7IsEaOgxVYW7O#rd
 */
public class Test10 {

    public static void main(String[] args) {
        // 外部普通类
        System.out.println("方法名             类名");
        System.out.println("getName            " + Test10.class.getName());
        System.out.println("getCanonicalName   " + Test10.class.getCanonicalName());
        System.out.println("getSimpleName      " + Test10.class.getSimpleName());
        System.out.println();

        // 内部类
        System.out.println("getName            " + TestInnerClass.class.getName());
        System.out.println("getCanonicalName   " + TestInnerClass.class.getCanonicalName());
        System.out.println("getSimpleName      " + TestInnerClass.class.getSimpleName());
        System.out.println();

        // 数组类
        TestInnerClass[] testInnerClasses = new TestInnerClass[]{
                new TestInnerClass(),
                new TestInnerClass(),
                new TestInnerClass()
        };
        System.out.println("getName            " + testInnerClasses.getClass().getName());
        System.out.println("getCanonicalName   " + testInnerClasses.getClass().getCanonicalName());
        System.out.println("getSimpleName      " + testInnerClasses.getClass().getSimpleName());
        System.out.println();

    }

    static class TestInnerClass {

    }

}
