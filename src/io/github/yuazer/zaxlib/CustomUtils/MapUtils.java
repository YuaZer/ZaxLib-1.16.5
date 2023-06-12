package io.github.yuazer.zaxlib.CustomUtils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    //将HashMap存为Yaml文件
    public void saveHashMapToYaml(HashMap<Object, Object> hashMap, File file) {
        // 加载 YAML 文件
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        // 清空配置文件
        config.set("", null);
        // 将 HashMap 写入配置文件
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            config.set(entry.getKey().toString(), entry.getValue());
        }
        // 保存配置文件
        try {
            config.save(file);
            System.out.println("HashMap saved to YAML file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从YAML文件获取HashMap
    public HashMap<Object, Object> loadHashMapFromYaml(File file) {
        // 加载 YAML 文件
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        // 读取配置文件并创建 HashMap
        HashMap<Object, Object> hashMap = new HashMap<>();
        ConfigurationSection section = config.getConfigurationSection("");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                Object value = config.get(key);
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }
}
