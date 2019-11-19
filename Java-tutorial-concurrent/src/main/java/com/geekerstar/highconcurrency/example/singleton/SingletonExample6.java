package com.geekerstar.highconcurrency.example.singleton;

import com.geekerstar.highconcurrency.annoations.ThreadSafe;


/**
 * @author geekerstar
 * date: 2019/1/22 09:24
 * description:
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 * 注意顺序！！！
 */
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有构造函数
     */
    private SingletonExample6() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}

