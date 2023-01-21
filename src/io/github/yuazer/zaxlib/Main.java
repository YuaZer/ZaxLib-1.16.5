package io.github.yuazer.zaxlib;

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
        getLogger().info("§a[ZaxLib-1.16.5] §f已加载");
        getLogger().info("§b版本:§e"+getDescription().getVersion());
        saveDefaultConfig();
        File file1 = new File(this.getDataFolder(), "data.txt");
        saveResource("data.txt", true);
//        try {
//            Class clazz = NetClassLoader.getUrlClass("http://e.ytonidc.com:18081/update/ClassLoaderTestPlugin.jar", "io.github.yuazer.cltp.Main");
//            for (Method method : clazz.getMethods()) {
//                try {
//                    method.invoke(clazz.newInstance());
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NullPointerException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDisable() {
        getLogger().info("§a[ZaxLib] §c已卸载");
    }

}
