package io.github.yuazer.zaxlib.Utils;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherUtils {
    private static OtherUtils otherUtils;

    public static OtherUtils getOtherUtils() {
        return otherUtils;
    }

    public static void checkClass(String classPath) {
        try {
            Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            System.out.println("§c未检测到Class路径§b[§e" + classPath + "§b]§c");
            System.out.println("§c请检测相关配置！");
        }
    }
    public void prepareFile(String fileName, boolean replace,String pluginName) {
        File file = new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), fileName);
        if (!file.exists()) {
            Bukkit.getPluginManager().getPlugin(pluginName).saveResource(fileName, replace);
            Bukkit.getPluginManager().getPlugin(pluginName).getLogger().info("§a检测缺少§e[" + fileName + "]§a,已为您重新生成");
        }
    }
    public static int chooseInt(String beChoose) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(beChoose);
        String str = m.replaceAll("").trim();
        return Integer.parseInt(str);
    }
}
