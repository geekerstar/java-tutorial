package com.geekerstar.wx.interrupt;

public class RunTaskCase4 {

    private Thread taskThread;

    public void start() {
        taskThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("doSomething");
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    // 重置中断标志位为true
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        taskThread.start();
    }

    public void stop() {
        taskThread.interrupt();
    }
}
