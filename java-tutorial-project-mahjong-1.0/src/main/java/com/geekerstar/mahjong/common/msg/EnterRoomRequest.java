package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class EnterRoomRequest implements MahjongMessage {
    private long roomId;
}
