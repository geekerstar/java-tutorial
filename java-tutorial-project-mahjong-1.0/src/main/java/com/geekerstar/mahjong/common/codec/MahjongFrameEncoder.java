package com.geekerstar.mahjong.common.codec;

import io.netty.handler.codec.LengthFieldPrepender;

public class MahjongFrameEncoder extends LengthFieldPrepender {
    public MahjongFrameEncoder() {
        super(2);
    }
}
