package io.github.yuazer.zaxlib.Verify;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Formatter;
import java.util.Locale;

public class verifyCheck {
    private static String isPass = "false";

    public static String getIsPass() {
        return isPass;
    }

    public static void checking() {
        String ip = "";
        try {
            ip = getmac().replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Socket socket = null;
        try {
            socket = new Socket("e.ytonidc.com", 18082);
            //连接到服务端
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalIp = ip;
        ConnectedSocket cs = new ConnectedSocket(socket, new MessageHandler() {
            @Override
            public void onMessageReceive(SocketEvent e, Object message) {
                /**
                 *当客户端接收到来自服务端发过来的消息时，进行判断
                 */
                String msg = message.toString();
                switch (msg) {
                    case "Z菌网络验证系统连接成功！":
                        break;
                    case "true":
                        isPass = "true";
                        e.getConnectedSocket().disConnect();
                        break;
                    case "netfail":
                    case "false":
                    default:
                        isPass = "false";
                        e.getConnectedSocket().disConnect();
                }
            }

            @Override
            public void onClientConnected(SocketEvent e) {
                e.getConnectedSocket().sendMessage(finalIp);//当客户端连接到服务端的时候，发送机械码给服务端
            }
        });
    }

    /**
     * 获取机械码
     */
    public static String getmac() throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        ni.getInetAddresses().nextElement().getAddress();
        byte[] mac = ni.getHardwareAddress();
        String sIP = address.getHostAddress();
        String sMAC = "";
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; ++i) {
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], i < mac.length - 1 ? "-" : "").toString();
        }
        return sMAC;
    }
}
