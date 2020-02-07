package com.geekerstar.threadlocal;

/**
 * @author geekerstar
 * @date 2020/2/7 21:47
 * @description 空指针异常情况
 */
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    // 这里long是基本类型，注意装箱拆箱，如果下面为空，返回的会NPE
    public long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread1.start();
//        threadLocalNPE.set();
//        System.out.println(threadLocalNPE.get());

    }
}
