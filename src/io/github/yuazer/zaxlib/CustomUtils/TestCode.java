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

public class TestCode {
    public static void main(String[] args) {
        byte[] bytes = NetClassLoader.classToByte(LoaderTest.class);
        ZaxClassLoader myClassLoader = new ZaxClassLoader();
        Class clazz = myClassLoader.ZaxdefineClass(LoaderTest.class.getName(),bytes);
        try {
            Object obj1 = clazz.newInstance();
            Constructor c = clazz.getDeclaredConstructor();
            Method method = clazz.getDeclaredMethod("say");
            method.setAccessible(false);
            method.invoke(obj1);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

