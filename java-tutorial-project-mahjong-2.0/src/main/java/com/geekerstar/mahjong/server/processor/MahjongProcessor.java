package com.geekerstar.mahjong.server.processor;

import com.google.protobuf.MessageLite;

public interface MahjongProcessor<T extends MessageLite> {
    void process(T message);
}
