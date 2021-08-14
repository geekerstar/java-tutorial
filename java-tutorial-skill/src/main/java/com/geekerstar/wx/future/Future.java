package com.geekerstar.wx.future;

public class Future {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕 "+System.currentTimeMillis());
        //...这里做一些其它任务
        System.out.println("数据："+data.getResult());
        System.out.println("获取完毕 "+System.currentTimeMillis());
    }
}
