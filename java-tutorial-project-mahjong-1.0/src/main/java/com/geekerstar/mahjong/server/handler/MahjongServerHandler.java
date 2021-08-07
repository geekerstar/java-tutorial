package com.geekerstar.mahjong.server.handler;

import com.geekerstar.mahjong.common.protocol.MahjongProtocol;
import com.geekerstar.mahjong.server.executor.MahjongEventExecutorGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MahjongServerHandler extends SimpleChannelInboundHandler<MahjongProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MahjongProtocol mahjongProtocol) throws Exception {
        log.info("receive msg: {}", mahjongProtocol);
        MahjongEventExecutorGroup.execute(ctx.channel(), mahjongProtocol);
    }
}
