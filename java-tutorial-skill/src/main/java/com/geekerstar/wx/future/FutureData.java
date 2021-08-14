package com.geekerstar.wx.future;

public class FutureData implements Data {
    private  RealData realData = null;
    private  boolean isReady = false;

    synchronized public void setRealData(RealData realData){
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll(); //通知所有等待的线程继续运行
    }

    @Override
    synchronized public String getResult() {
        while (!isReady){
            try {
                System.out.print("...waiting...");
                wait(); //使当前线程在此处进行等待，直到被通知后继续运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
