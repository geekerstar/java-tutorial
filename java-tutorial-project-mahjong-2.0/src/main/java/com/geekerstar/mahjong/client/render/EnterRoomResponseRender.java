package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.proto.EnterRoomResponse;

public class EnterRoomResponseRender implements MahjongRender<EnterRoomResponse> {
    @Override
    public void render(EnterRoomResponse message) {
        MockClient.enterRoomResponse(message);
    }
}
