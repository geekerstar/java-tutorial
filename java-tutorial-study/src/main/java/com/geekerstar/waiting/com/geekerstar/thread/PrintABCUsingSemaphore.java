package com.geekerstar.waiting.com.geekerstar.thread;

import java.util.concurrent.Semaphore;

/**
 * @author geekerstar
 * date: 2019-07-24 09:34
 * description:
 *
 * 三个线程分别打印A，B，C，要求这三个线程一起运行，打印n次，输出形如“ABCABCABC….”的字符串。
 */
public class PrintABCUsingSemaphore {
    private int times;
    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    public PrintABCUsingSemaphore(int times){
        this.times = times;
    }


    public static void main(String[] args) {
        PrintABCUsingSemaphore printABC = new PrintABCUsingSemaphore(10);

        //非静态方法引用 x::toString 和 () -> x.toString是等价的
        new Thread(printABC::printA).start();
        new Thread(printABC::printB).start();
        new Thread(printABC::printC).start();
    }

    public void printA(){
        try {
            print("A",semaphoreA,semaphoreB);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void printB(){
        try {
            print("B",semaphoreB,semaphoreC);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void printC(){
        try {
            print("C",semaphoreC,semaphoreA);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void print(String name,Semaphore current,Semaphore next) throws InterruptedException {
        for (int i = 0; i < times; i++){
            current.acquire();
            System.out.print(name);
            next.release();

        }
    }

}
