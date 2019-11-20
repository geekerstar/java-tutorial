package com.geekerstar.waiting.singleton;

/**
 * @author geekerstar
 * date: 2019-07-24 09:27
 * description:
 */
public class Singleton {
    /**
     * 懒汉式(双重检查加锁版本)
     */
//    //volatile保证党uniqueInstance变量被初始化为Singleton实例时，多个线程可以正确处理uniqueInstance变量
//    private volatile static Singleton uniqueInstance;
//    private Singleton(){
//
//    }
//    public static Singleton getInstance(){
//        //检查实例，如果不存在，就进入同步代码块
//        if (uniqueInstance == null){
//            //只有第一次才彻底执行这里的代码
//            synchronized (Singleton.class){
//                //进入同步代码块后，再检查异常，如果仍是null，才创建实例
//                if (uniqueInstance == null){
//                    uniqueInstance = new Singleton();
//                }
//            }
//        }
//        return uniqueInstance;
//    }

    /**
     * 静态内部类方式
     *
     * 静态内部实现的单例是懒加载的且线程安全。
     * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。
     */
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton(){}
    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
