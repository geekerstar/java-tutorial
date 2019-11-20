package com.geekerstar.thread.communication.farmerchild;

public class Child extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Kuang.kuang) {
                //1.筐里没水果了就让小孩休息
                if (Kuang.kuang.size() == 0) {
                    try {
                        Kuang.kuang.wait();
                    } catch (InterruptedException e) {
                    }
                }
                //2.小孩吃水果
                Kuang.kuang.remove("apple");
                System.out.println("小孩吃了一个水果,目前筐里有" + Kuang.kuang.size() + "个水果");
                //3.唤醒农夫继续放水果
                Kuang.kuang.notify();
            }
            //4.模拟控制速度
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
