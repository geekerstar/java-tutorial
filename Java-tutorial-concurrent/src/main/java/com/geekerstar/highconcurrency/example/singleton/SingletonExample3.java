package com.geekerstar.highconcurrency.example.singleton;

import com.geekerstar.highconcurrency.annoations.NotRecommend;
import com.geekerstar.highconcurrency.annoations.ThreadSafe;

/**
 * @author geekerstar
 * date: 2019/1/22 09:15
 * description:懒汉模式:单例实例在第一次使用时进行创建
 *          线程安全，不推荐
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
