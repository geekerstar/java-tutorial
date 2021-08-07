package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.domain.Room;
import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class RoomRefreshNotification implements MahjongMessage {
    private int operation;
    private Room room;
}
