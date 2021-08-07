package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.msg.OperationNotification;

public class OperationNotificationRender implements MahjongRender<OperationNotification> {
    @Override
    public void render(OperationNotification message) {
        MockClient.operationNotification(message);
    }
}
