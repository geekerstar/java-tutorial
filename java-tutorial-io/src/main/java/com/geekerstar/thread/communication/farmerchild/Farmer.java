package com.geekerstar.thread.communication.farmerchild;

public class Farmer extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Kuang.kuang) {
                //1.筐放满了就让农夫休息
                if (Kuang.kuang.size() == 10) {
                    try {
                        Kuang.kuang.wait();
                    } catch (InterruptedException e) {
                    }
                }
                //2.往筐里放水果
                Kuang.kuang.add("apple");
                System.out.println("农夫放了一个水果,目前筐里有" + Kuang.kuang.size()
                        + "个水果");
                //3.唤醒小孩继续吃
                Kuang.kuang.notify();
            }
            //4.模拟控制速度
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }
}
