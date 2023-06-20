package io.github.yuazer.zaxlib.SocketUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SocketServer {
    private ServerSocket serverSocket;
    private boolean running;
    private Map<Socket, PrintWriter> clientWriters;

    public SocketServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientWriters = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        System.out.println("Server started.");

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientWriters.put(clientSocket, writer);

                // 创建一个新线程处理客户端消息
                Thread clientThread = new Thread(() -> handleClient(clientSocket, writer));
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server stopped.");
    }

    private void handleClient(Socket clientSocket, PrintWriter writer) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received message from client: " + message);
                // 在这里根据不同的消息内容作出回复
                //byte[] classBytes = Files.readAllBytes(Paths.get("D:/Test.class"));
                byte[] classBytes = Files.readAllBytes(Paths.get("D:/ZPokeZombieSurgeLoader.class"));
                //sendMessage(clientSocket, classBytes);
                //System.out.println(Arrays.toString(classBytes));
                String replyMessage = "我是服务端呀~";
                sendMessage(clientSocket,replyMessage);
            }
            // 客户端关闭了连接
            clientWriters.remove(clientSocket);
            // 不主动关闭连接
            // clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(Socket clientSocket, String message) {
        try {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(message+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Socket socket, byte[] classBytes) {
        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            // 发送字节数组的长度
            outputStream.writeInt(classBytes.length);
            outputStream.flush();

            // 发送字节数组
            outputStream.write(classBytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClassFilePath(Class<?> clazz) {
        String className = clazz.getName();
        String classFilePath = clazz.getResource(className.replace('.', '/') + ".class").getFile();
        return new File(classFilePath).getAbsolutePath();
    }
}