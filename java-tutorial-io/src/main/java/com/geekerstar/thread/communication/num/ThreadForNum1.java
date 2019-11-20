package com.geekerstar.thread.communication.num;

//定义一个线程类  输出1
public class ThreadForNum1 extends Thread {

    @Override
    public void run(){
        for(int j=0;j<10;j++){
            synchronized (MyLock.o) {
                System.out.println(1);
                MyLock.o.notify();
                try {
                    MyLock.o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
