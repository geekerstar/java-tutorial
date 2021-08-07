package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.domain.Player;
import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class LoginResponse implements MahjongMessage {
    private boolean result;
    private Player player;
    private String message;
}
