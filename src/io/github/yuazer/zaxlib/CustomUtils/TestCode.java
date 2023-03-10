package io.github.yuazer.zaxlib.CustomUtils;

import io.github.yuazer.zaxlib.Utils.NetClassLoader;
import io.github.yuazer.zaxlib.Utils.ZaxClassLoader;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCode {
    public static void main(String[] args) {
//        byte[] bytes = NetClassLoader.classToByte(LoaderTest.class);
//        ZaxClassLoader myClassLoader = new ZaxClassLoader();
//        Class clazz = myClassLoader.ZaxdefineClass(LoaderTest.class.getName(),bytes);
//        try {
//            Object obj1 = clazz.newInstance();
//            Constructor c = clazz.getDeclaredConstructor();
//            Method method = clazz.getDeclaredMethod("say");
//            method.setAccessible(false);
//            method.invoke(obj1);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
        String str = "zaxpoints_cmi_usermate_zaxpoints_18";
        String regex = "zaxpoints_(.*?)_[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String rankAPI = matcher.group(1); // 获取第一个捕获组的内容
            System.out.println(rankAPI); // 输出 XXX_XXX_XXX
            int rankNumber = Integer.parseInt(str.replace(rankAPI+"_","").replace("zaxpoints_",""));
            System.out.println(rankNumber);
        }
    }
}

