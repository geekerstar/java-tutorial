package com.geekerstar.wx.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author geekerstar
 * @date 2021/8/12 10:37
 * @description
 */
public class TestTimer {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //每隔1秒调用一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test1");
            }
        }, 1000, 1000);
        //每隔3秒调用一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test2");
            }
        }, 3000, 3000);

    }
}
