package com.geekerstar.task;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2021/7/24 09:07
 * @description https://mp.weixin.qq.com/s/ucEFKf-5T-Fsfr27oFHTbQ
 */
public class Task1 {

    // while+sleep组合
//    public static void main(String[] args) {
//        final long timeInterval = 5000;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(Thread.currentThread().getName() + "每隔5秒执行一次");
//                    try {
//                        Thread.sleep(timeInterval);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }

    // 最小堆实现
//    public static void main(String[] args) {
//        Timer timer = new Timer();
//        //每隔1秒调用一次
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("test1");
//            }
//        }, 1000, 1000);
//        //每隔3秒调用一次
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("test2");
//            }
//        }, 3000, 3000);
//
//    }


    // ScheduledThreadPoolExecutor
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
