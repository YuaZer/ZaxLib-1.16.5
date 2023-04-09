package io.github.yuazer.zaxlib.CustomUtils;

import io.github.yuazer.zaxlib.Utils.NetClassLoader;
import io.github.yuazer.zaxlib.Utils.ZaxClassLoader;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCode {
    public static void main(String[] args) {
        double y = 1.965;
        for (int i = 9; i <= 36; i++) {
            DecimalFormat df = new DecimalFormat("#0.000");   //#.00 表示两位小数
            y = Double.parseDouble(df.format(y));
            System.out.println("背包道具" + i + "_slot:\n" +
                    "  x: \"界面变量.x*1.435\"\n" +
                    "  y: \"variable.scroll+界面变量.y*" + y + "\"\n" +
                    "  width: \"界面变量.x比*50/2\"\n" +
                    "  height: \"界面变量.y比*50/2\"\n" +
                    "  identifier: \"container_" + (i + 8) + "\"\n" +
                    "  limitX: \"界面变量.x*0\"\n" +
                    "  limitY: \"界面变量.y*0.722\"\n" +
                    "  limitWidth: \"1000\"\n" +
                    "  limitHeight: \"304\"\n" +
                    "  visible: false");
            y += 0.15;
        }
    }
}

