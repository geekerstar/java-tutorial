package com.geekerstar.waiting.design;

/**
 * @author geekerstar
 * date: 2019-07-24 09:55
 * description:
 *
 * 懒汉
 */
public class SingletonDemo1 {
    private static SingletonDemo1 instance;
    private SingletonDemo1(){}
    public static SingletonDemo1 getInstance(){
        if(instance==null){
            instance=new SingletonDemo1();
        }
        return instance;
    }

}
