package com.geekerstar.thread.communication.num;

//自定义一个线程类   输出2
public class ThreadForNum2 extends Thread {

    @Override
    public void run(){
        for(int j=0;j<10;j++){
            synchronized (MyLock.o) {
                System.out.println(2);
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
