package com.geekerstar.chatroom.bio.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/23 14:49
 * @description 服务端
 */
public class ChatServer {
    private int DEFAULT_PORT = 8888;
    private final String QUIT = "quit";

    private ServerSocket serverSocket;

    /**
     * 保存连接的客户端信息
     */
    private Map<Integer, Writer> connectedClients;

    public ChatServer() {
        connectedClients = new HashMap<>();
    }

    /**
     * 添加客户端
     *
     * @param socket
     */
    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            connectedClients.put(port, writer);
            System.out.println("客户端[" + port + "]已连接到服务器");
        }
    }

    /**
     * 移除客户端
     *
     * @param socket
     * @throws IOException
     */
    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectedClients.containsKey(port)) {
                connectedClients.get(port).close();
            }
            connectedClients.remove(port);
            System.out.println("客户端[" + port + "]已断开连接");
        }
    }

    /**
     * 转发消息
     *
     * @param socket
     * @param fwdMsg
     */
    public synchronized void forwardMessage(Socket socket, String fwdMsg) throws IOException {
        for (Integer id : connectedClients.keySet()) {
            // 排除发送者本人
            if (!id.equals(socket.getPort())) {
                Writer writer = connectedClients.get(id);
                writer.write(fwdMsg);
                writer.flush();
            }
        }
    }

    /**
     * 监听是否准备退出标志
     *
     * @param msg
     * @return
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    /**
     * 关闭serverSocket
     */
    public synchronized void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("关闭serverSocket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        try {
            // 监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口：" + DEFAULT_PORT + "...");

            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                // 创建ChatHandler线程
                new Thread(new ChatHandler(this, socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
}
