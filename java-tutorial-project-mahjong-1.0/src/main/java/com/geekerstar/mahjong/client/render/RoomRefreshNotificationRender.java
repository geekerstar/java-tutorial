package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.msg.RoomRefreshNotification;

public class RoomRefreshNotificationRender implements MahjongRender<RoomRefreshNotification> {
    @Override
    public void render(RoomRefreshNotification message) {
        MockClient.roomRefreshNotification(message);
    }
}
