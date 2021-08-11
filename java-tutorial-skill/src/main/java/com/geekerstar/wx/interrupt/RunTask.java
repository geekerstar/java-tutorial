package com.geekerstar.wx.interrupt;

// https://mp.weixin.qq.com/s/bS7FL3XVE5R8BsGTl4JbRg
public class RunTask {

    private volatile boolean stopFlag;
    private Thread taskThread;

    public void start() {
        taskThread = new Thread(() -> {
            while (!stopFlag) {
                System.out.println("doSomething");
            }
        });
        taskThread.start();
    }

    public void stop() {
        stopFlag = true;
    }


}
