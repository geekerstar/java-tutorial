package com.geekerstar.waiting.design;

/**
 * @author geekerstar
 * date: 2019-07-24 09:56
 * description:
 *
 * 饿汉
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance=new SingletonDemo2();
    private SingletonDemo2(){}
    public static SingletonDemo2 getInstance(){
        return instance;
    }

}
