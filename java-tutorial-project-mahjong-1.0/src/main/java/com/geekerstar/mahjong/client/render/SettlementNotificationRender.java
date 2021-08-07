package com.geekerstar.mahjong.client.render;

import com.geekerstar.mahjong.client.mock.MockClient;
import com.geekerstar.mahjong.common.msg.SettlementNotification;

public class SettlementNotificationRender implements MahjongRender<SettlementNotification> {
    @Override
    public void render(SettlementNotification message) {
        MockClient.settlementNotification(message);
    }
}
