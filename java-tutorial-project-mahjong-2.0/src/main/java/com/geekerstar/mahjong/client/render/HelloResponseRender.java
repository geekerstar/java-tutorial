package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.proto.HelloResponse;

public class HelloResponseRender implements MahjongRender<HelloResponse> {
    @Override
    public void render(HelloResponse message) {
        MockClient.helloResponse(message);
    }
}
