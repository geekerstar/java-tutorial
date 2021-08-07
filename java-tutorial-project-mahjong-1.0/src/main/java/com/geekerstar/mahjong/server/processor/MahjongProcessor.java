package com.geekerstar.mahjong.server.processor;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;

public interface MahjongProcessor<T extends MahjongMessage> {
    void process(T message);
}
