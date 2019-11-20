package com.geekerstar.waiting.singleton;

/**
 * @author geekerstar
 * date: 2019-08-03 11:08
 * description:
 */
public class Singleton2 {
//    // 饿汉式
//    // 直接创建对象
//    public static Singleton2 instance = new Singleton2();
//
//    // 私有化构造函数
//    private Singleton2(){
//
//    }
//
//    // 返回对象实例
//    public static Singleton2 getInstance(){
//        return instance;
//    }

    /**
     * 懒汉式
     */
    // 声明变量
    private static volatile Singleton2 singleton2 = null;

    // 私有化构造函数
    private Singleton2(){

    }

    // 提供对外方法
    public static Singleton2 getInstance(){
        if (singleton2 == null){
            synchronized (Singleton2.class){
                if (singleton2 == null){
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
