package com.geekerstar.wx.schedule;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2021/8/12 10:40
 * @description
 */
public class TestScheduledThreadPoolExecutor {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        //启动1秒之后，每隔1秒执行一次
        executor.scheduleAtFixedRate((new Runnable() {
            @Override
            public void run() {
                System.out.println("test3");
            }
        }),1,1, TimeUnit.SECONDS);
        //启动1秒之后，每隔3秒执行一次
        executor.scheduleAtFixedRate((new Runnable() {
            @Override
            public void run() {
                System.out.println("test4");
            }
        }),1,3, TimeUnit.SECONDS);
    }
}
