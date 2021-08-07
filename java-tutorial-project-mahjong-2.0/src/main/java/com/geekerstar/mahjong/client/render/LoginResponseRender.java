package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.proto.LoginResponse;

public class LoginResponseRender implements MahjongRender<LoginResponse> {
    @Override
    public void render(LoginResponse message) {
        MockClient.loginResponse(message);
    }
}
