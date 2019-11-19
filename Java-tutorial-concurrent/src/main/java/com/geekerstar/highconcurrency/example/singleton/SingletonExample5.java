package com.geekerstar.highconcurrency.example.singleton;

import com.geekerstar.highconcurrency.annoations.ThreadSafe;


/**
 * @author geekerstar
 * date: 2019/1/22 09:22
 * description:
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建  线程安全 用volatile
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有构造函数
     */
    private SingletonExample5() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    /**
     * 单例对象 volatile + 双重检测机制 -> 禁止指令重排
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample5 getInstance() {
        // 双重检测机制        // B
        if (instance == null) {
            // 同步锁
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    // A - 3
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}

