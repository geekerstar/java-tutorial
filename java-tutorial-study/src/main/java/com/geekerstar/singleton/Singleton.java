package com.geekerstar.singleton;

/**
 * @author geekerstar
 * date: 2019/3/10 17:28
 * description: 利用静态内部内实现单例模式
 *
 * 延迟加载，线程安全
 */
public class Singleton {
    private Singleton(){}

    private static class T{
        /**
         * 静态内部类在使用的时候才加载，且只加载一次
         */
        private static Singleton t = new Singleton();
    }

    public static Singleton getInstance(){
        return T.t;
    }
}
