package com.geekerstar.wx.future;

public class RealData implements Data {
    protected String result;

    public RealData(String para) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = "["+para+"]";
    }

    @Override
    public String getResult() {
        return result;
    }
}
