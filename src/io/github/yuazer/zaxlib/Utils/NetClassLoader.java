package io.github.yuazer.zaxlib.Utils;

import io.github.yuazer.cltp.Main;
import io.github.yuazer.cltp.TestInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class NetClassLoader {
    public static Class getUrlClass(String jarUrl, String classFullName) {
        Class clazz1 = null;
        try {
            URL[] urls = new URL[]{new URL(jarUrl)};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            clazz1 = urlClassLoader.loadClass(classFullName);
            return clazz1;
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz1;
    }

    public static void main(String[] args) throws Exception {
        URL[] urls = new URL[]{new URL("https://yuazer.github.io/myH5/ClassLoaderTestPlugin.jar")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class clazz1 = urlClassLoader.loadClass("io.github.yuazer.cltp.Main");
        clazz1.getDeclaredConstructor().setAccessible(true);
        Object obj1 = clazz1.newInstance();
        Method m = clazz1.getDeclaredMethod("sout");
        m.invoke(obj1);
    }
}
