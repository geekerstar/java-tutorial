package com.geekerstar.thread.safe;

//同步方法  默认使用this作为锁
public class SaleWindow2 implements Runnable {

    /**
     * 表示10张火车票   这是共享资源
     */
    private int id = 10;

    public synchronized  void saleOne(){
        if (id > 0) {
            System.out.println(Thread.currentThread().getName()
                    + "卖了编号为" + id + "的火车票");
            id--;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * 卖10张火车票
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
           saleOne();
        }
    }
}
