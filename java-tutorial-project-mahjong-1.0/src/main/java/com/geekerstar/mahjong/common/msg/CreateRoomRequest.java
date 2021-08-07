package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class CreateRoomRequest implements MahjongMessage {
    private int playerNum;
    private int baseScore;
}
