package com.geekerstar.singleton;

/**
 * @author geekerstar
 * date: 2019/3/10 17:32
 * description: 双重锁校验（DCL） 使用Volatile场景之一
 */
public class Singleton2 {
    /**
     * 需要使用volatile关键字
     */
    private static volatile Singleton2 instance = null;

    private Singleton2(){}

    /**
     * 双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
     * @return
     */
    public static Singleton2 getInstance(){
        if (instance == null){
            synchronized (Singleton2.class){
                if (instance == null){
                    //非原子操作
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
