package com.geekerstar.asset.a2.m1;

/**
 * @author geekerstar
 * @date 2021/7/31 09:02
 * @description
 */
public class VolatileTest {

    private static class ShowVisibility implements Runnable {
        public static Object o = new Object();
//        private volatile Boolean flag = false;
        private Boolean flag = false;

        @Override
        public void run() {
            while (true) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + flag);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ShowVisibility showVisibility = new ShowVisibility();
        Thread visableThread = new Thread(showVisibility);
        visableThread.start();
        //给线程启动的时间
        Thread.sleep(500);
        //更新flay
        showVisibility.flag = true;
        System.out.println("flag is true, thread should print");
        Thread.sleep(1000);
        System.out.println("I have slept 1 seconds. Is there anything printed ?");
    }

}
