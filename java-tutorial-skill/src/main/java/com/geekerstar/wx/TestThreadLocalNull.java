package com.geekerstar.wx;

/**
 * @author geekerstar
 * @date 2021/8/13 20:54
 * @description
 *
 * https://mp.weixin.qq.com/s/ZqMkRXKeZ6QU-Imy3maYuQ
 */
public class TestThreadLocalNull {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal();

    public static void set() {
        threadLocal.set(1L);
    }

    public static long get() {
        return threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            set();
            System.out.println(get());
        }).start();
        // 目的就是为了让子线程先运行完
        Thread.sleep(100);
        System.out.println(get());
    }
}
