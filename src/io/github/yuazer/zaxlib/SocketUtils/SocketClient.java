package io.github.yuazer.zaxlib.SocketUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private boolean running;

    public SocketClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() {
        try {
            socket = new Socket(serverAddress, serverPort);
            running = true;
            Thread inputThread = new Thread(this::handleServerMessages);
            inputThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleServerMessages() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            while (running) {
                String message;
                while ((message = reader.readLine()) != null) {
                    if (!message.isEmpty()) {
                        System.out.println("Received message from server: " + message);
                        // 在这里处理收到的消息
                    }
                }
                disconnect();
            }
        } catch (NegativeArraySizeException exception) {
            System.out.println("重连中...");
            handleServerMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
