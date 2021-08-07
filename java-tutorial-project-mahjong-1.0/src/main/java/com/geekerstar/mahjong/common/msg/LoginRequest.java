package com.geekerstar.mahjong.common.msg;

import com.geekerstar.mahjong.common.protocol.MahjongMessage;
import lombok.Data;

@Data
public class LoginRequest implements MahjongMessage {
    private String username;
    private String password;
}
