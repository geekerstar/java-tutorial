package com.geekerstar.mahjong.client.handler;

import com.google.protobuf.MessageLite;
import com.geekerstar.mahjong.client.render.MahjongRender;
import com.geekerstar.mahjong.client.render.MahjongRenderManager;
import com.geekerstar.mahjong.common.protocol.MahjongProtocol;
import com.geekerstar.mahjong.common.protocol.MahjongProtocolHeader;
import com.geekerstar.mahjong.common.util.MahjongContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MahjongClientHandler extends SimpleChannelInboundHandler<MahjongProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MahjongProtocol mahjongProtocol) throws Exception {
        // 协议头
        MahjongProtocolHeader header = mahjongProtocol.getHeader();
        // 协议体
        MessageLite message = mahjongProtocol.getBody();
        // 查找渲染器
        MahjongRender mahjongRender = MahjongRenderManager.choose(message);
        if (mahjongRender != null) {
            MahjongContext.currentContext().setCurrentChannel(ctx.channel());
            mahjongRender.render(message);
        } else {
            log.error("not found render, msgType={}", message.getClass().getName());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("something error", cause);
    }
}
