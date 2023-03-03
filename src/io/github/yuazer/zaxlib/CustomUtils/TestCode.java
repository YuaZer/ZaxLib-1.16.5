package io.github.yuazer.zaxlib.CustomUtils;


import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestCode {
    /**
     * 水仙花数的定义：一个三位数（100~999）的各位数的立方和等于它本身。输入一个数n，求这个100到这个数的区间内有多少个水仙花数，并打印出来，用空格分隔。若区间没有水仙花数，则输出Not Found.
     *
     * @param n 整数
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入n的值!");
        int n = scanner.nextInt();
        int amount = 0;
        if (n<100){
            System.out.println("n必须大于等于100哦!");
        }else {
            System.out.println("水仙花数分别是:");
            for (n=n;n<=999;n++){
                int c = n/100;
                int b = (n/10)%10;
                int a = n%10;
                if ((c*c*c+b*b*b+a*a*a)==n){
                    System.out.println(n);
                    amount++;
                }
            }
        }
        if (amount == 0){
            System.out.println("Not Found.");
        }
    }
}

