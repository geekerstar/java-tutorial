package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.msg.CreateRoomResponse;

public class CreateRoomResponseRender implements MahjongRender<CreateRoomResponse> {
    @Override
    public void render(CreateRoomResponse message) {
        MockClient.createRoomResponse(message);
    }
}
