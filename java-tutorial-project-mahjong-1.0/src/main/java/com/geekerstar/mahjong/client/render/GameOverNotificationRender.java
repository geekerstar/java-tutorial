package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.msg.GameOverNotification;

public class GameOverNotificationRender implements MahjongRender<GameOverNotification> {
    @Override
    public void render(GameOverNotification message) {
        MockClient.gameOverNotification(message);
    }
}
