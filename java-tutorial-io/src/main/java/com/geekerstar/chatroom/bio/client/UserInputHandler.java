package com.geekerstar.chatroom.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author geekerstar
 * @date 2020/2/23 15:26
 * @description
 */
public class UserInputHandler implements Runnable {
    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        BufferedReader consoleReader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        while (true) {
            try {
                String input = consoleReader.readLine();

                // 向服务器发送消息
                chatClient.send(input);

                // 检查用户是否准备退出
                if (chatClient.readyToQuit(input)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
