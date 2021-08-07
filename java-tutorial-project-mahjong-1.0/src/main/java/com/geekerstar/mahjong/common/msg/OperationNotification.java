package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class OperationNotification implements MahjongMessage {
    private int operation;
    private int pos;
    private byte fireCard;
}
