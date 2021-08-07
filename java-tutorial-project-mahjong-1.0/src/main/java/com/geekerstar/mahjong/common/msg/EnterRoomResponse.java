package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class EnterRoomResponse implements MahjongMessage {
    private boolean result;
    private String message;
}
