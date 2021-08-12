package com.geekerstar.wx.schedule;

/**
 * @author geekerstar
 * @date 2021/8/12 10:36
 * @description
 */
public class TestWhileSleep {

    public static void main(String[] args) {
        final long timeInterval = 5000;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "每隔5秒执行一次");
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
