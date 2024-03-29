package io.github.yuazer.zaxlib.Utils;

import io.github.yuazer.cltp.Main;
import io.github.yuazer.cltp.TestInterface;
import io.github.yuazer.zaxlib.CustomUtils.EncryptUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class NetClassLoader {
    /**
     * 从URL获取Class
     *
     * @param jarUrl        目标jar的URL链接
     * @param classFullName class路径
     * @return Class
     */
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
    /**
     * 从经过Base64加密后的URL获取Class
     *
     * @param encodeJarUrl        目标jar的URL链接
     * @param classFullName class路径
     * @return Class
     */
    public static Class getUrlClass_decode(String encodeJarUrl, String classFullName) {
        Class clazz1 = null;
        try {
            String decodedUrl = new String(Base64.getUrlDecoder().decode(encodeJarUrl), "UTF-8");
            URL[] urls = new URL[]{new URL(decodedUrl)};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            clazz1 = urlClassLoader.loadClass(classFullName);
            return clazz1;
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return clazz1;
    }

    public static Class getUrlClass_Encrypt(String EncryptjarUrl, String classFullName, String key) {
        Class clazz1 = null;
        try {
            String jarUrl = EncryptUtil.decryptAES(EncryptjarUrl, key);
            URL[] urls = new URL[]{new URL(jarUrl)};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            clazz1 = urlClassLoader.loadClass(classFullName);
            return clazz1;
        } catch (MalformedURLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clazz1;
    }

    /**
     * 从URL获取YAML文件
     *
     * @param Url Yaml文件的URL链接
     * @return YamlConfiguration
     */
    public static YamlConfiguration getYamlFromUrl(String Url) {
        InputStreamReader reader = null;
        try {
            URL url = new URL(Url);
            InputStream in = url.openStream();// 从 URL 中获取文件byte流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//byte输出流
            byte[] buffer = new byte[4096]; //定义4096长度的byte数组
            int n;
            while ((n = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n); //把文件的字节输出流写进buffer
            }
            byte[] fileBytes = outputStream.toByteArray(); //将byte集合输出流转为byte数组
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes); //将byte数组写进byte数组输入流
            reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            //从Reader读取YAML文件
            return YamlConfiguration.loadConfiguration(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return YamlConfiguration.loadConfiguration(reader);
    }

    /**
     * 将Class转化为byte[]
     *
     * @param clazz 需要被转为byte[]的Class对象
     * @return byte[]
     */
    public static byte[] classToByte(Class clazz) {
        String resourceName = clazz.getName().replace(".", "/") + ".class";
        InputStream input = clazz.getClassLoader().getResourceAsStream(resourceName);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (true) {
            try {
                if (!((n = input.read(buffer)) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
//        byte[] bytes = new byte[8096];
//        try {
//            bytes = Files.readAllBytes(Paths.get(clazz.getResource(clazz.getSimpleName() + ".class").toURI()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//        return bytes;
    }

    public static byte[] classToByte_Obj(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    /**
     * byte[]转为class
     */
    public static Class<?> deserializeClass(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Class<?>) ois.readObject();
    }

    public static void main(String[] args) {
        try {
//            URL[] urls = new URL[]{new URL("https://yuazer.github.io/myH5/ClassLoaderTestPlugin.jar")};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls);
//            Class clazz1 = urlClassLoader.loadClass("io.github.yuazer.cltp.Main");
            //Class clazz1 = getUrlClass("https://yuazer.github.io/myH5/ClassLoaderTestPlugin.jar","io.github.yuazer.cltp.Main");
            Class<?> clazz1 = io.github.yuazer.cltp.Main.class;
            Object obj1 = clazz1.newInstance();
            Constructor c = clazz1.getDeclaredConstructor();
            Method m = clazz1.getDeclaredMethod("test2", String.class);
            c.setAccessible(true);
            m.setAccessible(true);
            System.out.println(m.invoke(obj1, new Object[]{"我是自定义的参数呀~"}));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("类或方法不存在");
        }
    }
}
