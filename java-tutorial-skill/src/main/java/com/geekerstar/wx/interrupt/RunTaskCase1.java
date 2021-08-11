package com.geekerstar.wx.interrupt;

public class RunTaskCase1 {

    private Thread taskThread;

    public void start() {
        taskThread = new Thread(() -> {
            while (true) {
                System.out.println("doSomething");
            }
        });
        taskThread.start();
    }

    public void stop() {
        taskThread.interrupt();
    }
}
