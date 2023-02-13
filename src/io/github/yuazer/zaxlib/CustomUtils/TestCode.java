package io.github.yuazer.zaxlib.CustomUtils;


import io.github.yuazer.zaxlib.Utils.YamlUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestCode {
    public static void main(String[] args) {
        initWindow();
    }
    public static void initWindow(){
        JFrame jFrame = new JFrame("GraceZ Mod[1.16.5]");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400,500);
        jFrame.setVisible(true);
    }
//    public static Object getKey(HashMap map, Object value) {
//        List<Object> keyList = new ArrayList<>();
//        for (Object key : map.keySet()) {
//            if (map.get(key).equals(value)) {
//                keyList.add(key);
//            }
//        }
//        return keyList;
//    }
//    public static void readTxtFile(String filePath) {
//        try {
//            List<String> keyList = new ArrayList<>();
//            String encoding = "GBK";
//            File file = new File(filePath);
//            if (file.isFile() && file.exists()) { //判断文件是否存在
//                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    keyList.add(lineTxt);
//                    System.out.println(keyList);
//                }
//                read.close();
//            } else {
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e) {
//            System.out.println("读取文件内容出错");
//            e.printStackTrace();
//        }
//    }
}

