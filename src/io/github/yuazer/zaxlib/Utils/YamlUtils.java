package io.github.yuazer.zaxlib.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class YamlUtils {

    /**
     * 返回Config.yml的指定String
     */
    public static String getConfigMessage(String path,String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString(path).replace("&", "§");
    }

    public static void createYamlConfig(String fileName,String pluginName, boolean replace) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        if (!file.exists()) {
            Bukkit.getPluginManager().getPlugin(pluginName).saveResource(fileName, replace);
        }
    }

    /**
     * 获取指定路径下所有文件夹/文件名
     * directoryPath 路径名
     * isAddDirectory 是否将子文件夹的路径也添加到list集合中
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getName());
                }
                list.addAll(getAllFile(file.getName(),isAddDirectory));
            } else {
                list.add(file.getName());
            }
        }
        return list;
    }

    public static double getConfigDouble(String path,String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    /**
     * fileName 举例：
     * - data.yml
     */
    public static FileConfiguration getFile(String fileName,String pluginName) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFile(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static File setFile(String fileName,String pluginName) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        return file;
    }

    public static boolean getConfigBoolean(String path,String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean(path);
    }

    /**
     * 获取其他配置文件指定String
     */
    public static String getYamlString(String fileName,String pluginName, String textPath) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        return conf.getString(textPath).replace("&", "§");
    }

    public static boolean getYamlBoolean(String fileName,String pluginName, String textPath) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        return conf.getBoolean(textPath);
    }

    public static boolean checkListContains(List<String> list, String checkString) {
        return list.contains(checkString);
    }

    public static List<String> getYamlStringList(String fileName,String pluginName, String textPath) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        return conf.getStringList(textPath);
    }

    public static List<String> getConfigStringList(String path,String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getStringList(path);
    }

    public static int getConfigInt(String path,String pluginName) {
        try {
            return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt(path);
        } catch (NullPointerException e){
            return 0;
        }
    }

    /**
     * 添加String到指定YAML文件
     */
    public static void addYamlString(File yamlFile, String path, String text) {
        try {
            if (!yamlFile.exists()) {
                System.out.println("该文件不存在");
                return;
            }
            FileConfiguration conf = YamlConfiguration.loadConfiguration(yamlFile);
            List<String> tempList = conf.getStringList(path);
            tempList.add(text);
            conf.set(path, tempList);
            conf.save(yamlFile);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
