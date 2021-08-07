package com.geekerstar.mahjong.server.processor;

import com.geekerstar.mahjong.common.proto.HelloRequest;
import com.geekerstar.mahjong.common.proto.HelloResponse;
import com.geekerstar.mahjong.common.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloRequestProcessor implements MahjongProcessor<HelloRequest> {

    @Override
    public void process(HelloRequest message) {
        log.info("receive hello request: {}", message);
        HelloResponse response = HelloResponse
                .newBuilder()
                .setMessage("你好，" + message.getName())
                .build();
        MessageUtils.sendResponse(response);
    }
}
