package io.github.yuazer.zaxlib;

import io.github.yuazer.zaxlib.Utils.PluginWelCome;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class Main extends JavaPlugin{
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        PluginWelCome.logLoaded(this);
        saveDefaultConfig();
        File file1 = new File(this.getDataFolder(), "data.txt");
        saveResource("data.txt", true);
    }
    @Override
    public void onLoad(){
        String targetPluginName = "ItemsAdders"; // 要查询路径的目标插件名
        Plugin targetPlugin = getServer().getPluginManager().getPlugin(targetPluginName);
        if (targetPlugin != null) {
            String jarPath = targetPlugin.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            getLogger().info("插件 " + targetPluginName + " 的 JAR 路径为：" + jarPath);
        } else {
            getLogger().warning("找不到插件 " + targetPluginName);
        }
    }

    @Override
    public void onDisable() {
        PluginWelCome.logDisable(this);
    }

}
