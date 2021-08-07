package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;

public interface MahjongRender<T extends MahjongMessage> {
    void render(T message);
}
