package io.github.yuazer.zaxlib.Utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YAMLArrayUtils {
    private File file;
    private YamlConfiguration config;

    public YAMLArrayUtils(String filePath) {
        this.file = new File(filePath);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public List<Map<?, ?>> getArray(String path) {
        return config.getMapList(path);
    }

    public void setArray(String path, List<Map<?, ?>> newArray) {
        config.set(path, newArray);
        saveConfig();
    }

    public void updateArrayElement(String path, int index, Map<?, ?> updatedElement) {
        List<Map<?, ?>> array = getArray(path);
        if (index >= 0 && index < array.size()) {
            array.set(index, updatedElement);
            setArray(path, array);
        }
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}