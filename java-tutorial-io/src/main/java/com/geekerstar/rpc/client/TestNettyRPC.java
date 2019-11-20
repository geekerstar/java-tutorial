package com.geekerstar.rpc.client;

import com.geekerstar.rpc.clientStub.NettyRPCProxy;

//服务调用方
public class TestNettyRPC {
    public static void main(String [] args){

        //第1次远程调用
        HelloNetty helloNetty=(HelloNetty) NettyRPCProxy.create(HelloNetty.class);
        System.out.println(helloNetty.hello());

        //第2次远程调用
        HelloRPC helloRPC =  (HelloRPC) NettyRPCProxy.create(HelloRPC.class);
        System.out.println(helloRPC.hello("RPC"));

    }
}
