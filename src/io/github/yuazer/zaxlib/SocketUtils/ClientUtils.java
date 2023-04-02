package io.github.yuazer.zaxlib.SocketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUtils {
    /**
     * 使用方法：
     *
     * ```java
     * // 服务端
     * SocketServer server = new SocketServer(8080, message -> {
     *     System.out.println("收到消息：" + message);
     *     try {
     *         server.broadcast("你好，客户端！");
     *     } catch (InterruptedException e) {
     *         e.printStackTrace();
     *     }
     * });
     * server.start();
     *
     * // 客户端
     * SocketConnection client = new SocketConnection("localhost", 8080, message -> {
     *     System.out.println("收到消息：" + message);
     * });
     * client.start();
     * client.sendMessage("你好，服务端！");
     * */
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final MessageHandler messageHandler;

    public ClientUtils(String host, int port, MessageHandler messageHandler) throws IOException {
        this.socket = new Socket(host, port);
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.messageHandler = messageHandler;
    }

    public void start() {
        new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    int length = inputStream.readInt();
                    byte[] bytes = new byte[length];
                    inputStream.readFully(bytes);
                    String message = new String(bytes);
                    messageHandler.handleMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    public void sendMessage(String message) throws IOException {
        byte[] bytes = message.getBytes();
        outputStream.writeInt(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface MessageHandler {
        void handleMessage(String message);
    }
}
