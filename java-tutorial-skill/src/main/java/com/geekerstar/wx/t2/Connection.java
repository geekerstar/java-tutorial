package com.geekerstar.wx.t2;

public class Connection implements AutoCloseable {
    public void sendData() {
        System.out.println("正在发送数据");
    }
    @Override
    public void close() throws Exception {
        System.out.println("正在关闭连接");
    }
}
