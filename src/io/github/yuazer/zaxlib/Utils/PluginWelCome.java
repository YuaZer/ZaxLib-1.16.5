package io.github.yuazer.zaxlib.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class PluginWelCome {
    /**
     * 加载插件时输出文本“[插件名] 已加载”。
     *
     * @param plugin 插件
     */
    public static void logLoaded(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §f已加载", plugin.getName()));
        Bukkit.getLogger().info("§b作者:§eZ菌");
        Bukkit.getLogger().info("§b版本:§e" + plugin.getDescription().getVersion());
    }
    public static void logDisable(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §c已卸载", plugin.getName()));
    }
    /**
     * 检测前置是否加载
     * */
    public static void checkDepend(JavaPlugin beLoad, List<String> DependName){
        System.out.println("§e正在检测需要的前置插件:§b" + DependName);
        for (String pluginName:DependName){
            if (Bukkit.getPluginManager().getPlugin(pluginName)==null){
                Bukkit.getPluginManager().disablePlugin(beLoad);
                System.out.println("§c缺少前置插件:§e" + pluginName);
                logDisable(beLoad);
                return;
            }
        }
        System.out.println("§a前置插件完整!插件正在加载!");
    }
}
