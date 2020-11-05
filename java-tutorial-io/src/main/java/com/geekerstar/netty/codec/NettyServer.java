package com.geekerstar.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup pGroup = new NioEventLoopGroup(); //线程组：用来处理网络事件处理（接受客户端连接）
        EventLoopGroup cGroup = new NioEventLoopGroup(); //线程组：用来进行网络通讯读写

        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class) //注册服务端channel
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    public void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast("decoder", new ProtobufDecoder(BookMessage.Book.getDefaultInstance()));
                        sc.pipeline().addLast(new NettyServerHandler());
                    }
                });
        ChannelFuture cf = b.bind(9999).sync();
        System.out.println("......Server is Starting......");

        //释放
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
