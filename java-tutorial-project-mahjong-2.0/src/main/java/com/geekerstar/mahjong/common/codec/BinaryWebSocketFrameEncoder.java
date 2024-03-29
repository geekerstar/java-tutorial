package com.geekerstar.mahjong.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

@ChannelHandler.Sharable
public class BinaryWebSocketFrameEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
//        ReferenceCountUtil.retain(msg);
        ReferenceCountUtil.retain(msg);
        BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(msg);
        out.add(binaryWebSocketFrame);
    }
}
