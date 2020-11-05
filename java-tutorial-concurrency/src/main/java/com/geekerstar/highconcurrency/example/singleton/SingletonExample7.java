package com.geekerstar.highconcurrency.example.singleton;

import com.geekerstar.highconcurrency.annoations.Recommend;
import com.geekerstar.highconcurrency.annoations.ThreadSafe;

/**
 * @author geekerstar
 * date: 2019/1/22 09:27
 * description: 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**
     * 私有构造函数
     */
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * 私有枚举类
     */
    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
