package com.geekerstar.highconcurrency.example.threadLocal;

/**
 * @author geekerstar
 * date: 2019/1/22 10:07
 * description:
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
