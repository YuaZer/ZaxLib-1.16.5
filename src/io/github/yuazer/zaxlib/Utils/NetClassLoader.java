package io.github.yuazer.zaxlib.Utils;

import io.github.yuazer.cltp.Main;
import io.github.yuazer.cltp.TestInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class NetClassLoader {
    public static Object getUrlObj(String jarURL, String classFullName) throws ClassNotFoundException {
        try {
            File file = new File(io.github.yuazer.zaxlib.Main.getInstance().getDataFolder(), "data.txt");
            //File file = new File("C://data.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s = new String();
            while ((s = in.readLine()) != null) {
                URL url = new URL(s);
                s = null;
                URL url1 = new URL(jarURL);
                URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                Class myClass = myClassLoader.loadClass(classFullName);
                Object obj = myClass.newInstance();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Class getUrlClass(String jarURL, String classFullName) throws ClassNotFoundException {
        try {
            File file = new File(io.github.yuazer.zaxlib.Main.getInstance().getDataFolder(), "data.txt");
            //File file = new File("C://data.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s = new String();
            while ((s = in.readLine()) != null) {
                URL url = new URL(s);
                s = null;
                URL url1 = new URL(jarURL);
                URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                Class myClass = myClassLoader.loadClass(classFullName);
                return myClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Main obj = (Main) getUrlClass("http://e.ytonidc.com:18081/update/ClassLoaderTestPlugin.jar", "io.github.yuazer.cltp.Main").newInstance();
        //Main obj = (Main) getUrlObj("http://e.ytonidc.com:18081/update/ClassLoaderTestPlugin.jar", "io.github.yuazer.cltp.Main");
        System.out.println(obj.test());
    }
}
