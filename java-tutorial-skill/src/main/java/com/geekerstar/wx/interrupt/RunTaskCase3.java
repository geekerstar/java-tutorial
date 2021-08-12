package com.geekerstar.wx.interrupt;

import java.util.concurrent.TimeUnit;

public class RunTaskCase3 {

    private volatile boolean stopFlag;
    private Thread taskThread;

    public void start() {
        taskThread = new Thread(() -> {
            while (stopFlag) {
                try {
                    System.out.println("doSomething");
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        taskThread.start();
    }

    public void stop() {
        stopFlag = true;
        taskThread.interrupt();
    }
}
