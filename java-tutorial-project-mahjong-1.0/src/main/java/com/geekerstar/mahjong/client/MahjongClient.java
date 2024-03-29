package com.geekerstar.mahjong.client;

import com.geekerstar.mahjong.client.handler.MahjongClientHandler;
import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.codec.MahjongFrameDecoder;
import com.geekerstar.mahjong.common.codec.MahjongFrameEncoder;
import com.geekerstar.mahjong.common.codec.MahjongProtocolDecoder;
import com.geekerstar.mahjong.common.codec.MahjongProtocolEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class MahjongClient {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    public static void main(String[] args) throws Exception {
        // 工作线程池
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // 打印日志
//                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    // 一次编解码器
                    pipeline.addLast(new MahjongFrameDecoder());
                    pipeline.addLast(new MahjongFrameEncoder());
                    // 二次编解码器
                    pipeline.addLast(new MahjongProtocolDecoder());
                    pipeline.addLast(new MahjongProtocolEncoder());
                    // 处理器
                    pipeline.addLast(new MahjongClientHandler());
                }
            });

            // 连接到服务端
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(PORT)).sync();

            log.info("connect to server success");

            MockClient.start(future.channel());

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
