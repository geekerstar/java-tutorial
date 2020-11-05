package com.geekerstar.highconcurrency.example.singleton;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;

/**
 * @author geekerstar
 * date: 2019/1/22 09:12
 * description:懒汉模式:单例实例在第一次使用时进行创建
 *
 * 安全发布对象四个方法
 *      1、在静态初始化函数中初始化一个对象引用
 *      2、将对象的引用保存到volatile类型域或者AtomicReference对象中
 *      3、将对象的引用保存到某个正确构造对象的final类型域中
 *      4、将对象的引用保存到一个由锁保存的域中
 *
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数
     */
    private SingletonExample1() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}

