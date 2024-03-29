package io.github.yuazer.zaxlib.CustomUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Nameable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {
    public static String getInventoryTitle(Inventory inventory) {
        return ((Nameable) inventory.getHolder()).getCustomName();
    }
    /**
     * 将给定的Inventory对象存储为YAML文件
     *
     * @param inventory 需要被存储的Inventory对象
     * @param file      需要被存储的文件
     * @throws IOException 如果文件无法被创建或写入时抛出异常
     */
    public static void saveInventoryToFile(Inventory inventory, File file) throws IOException {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection inventorySection = config.createSection("inventory");
        inventorySection.set("title", getInventoryTitle(inventory));
        inventorySection.set("size", inventory.getSize());

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                inventorySection.set("item." + i, inventory.getItem(i));
            }
        }

        config.save(file);
    }

    /**
     * 从给定的YAML文件中读取Inventory对象
     *
     * @param file 包含Inventory数据的YAML文件
     * @return 生成的Inventory对象
     * @throws IOException 如果文件无法被读取时抛出异常
     */
    public static Inventory loadInventoryFromFile(File file) throws IOException {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String title = config.getString("inventory.title");
        int size = config.getInt("inventory.size");
        Inventory inventory = Bukkit.createInventory(null, size, title);

        ConfigurationSection itemSection = config.getConfigurationSection("inventory.item");
        if (itemSection != null) {
            for (String key : itemSection.getKeys(false)) {
                int index = Integer.parseInt(key);
                inventory.setItem(index, itemSection.getItemStack(key));
            }
        }

        return inventory;
    }
    /**
     * 获取非空气物品集合
     * */
    public static List<ItemStack> getNonAirItemStacks(Inventory inventory) {
        List<ItemStack> nonAirItemStacks = new ArrayList<>();
        ItemStack[] contents = inventory.getContents();
        for (ItemStack itemStack : contents) {
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                nonAirItemStacks.add(itemStack);
            }
        }
        return nonAirItemStacks;
    }
    /**
     * 获取非空气并且有Lore的物品集合
     * */
    public static List<ItemStack> getItemStacksWithLore(Inventory inventory) {
        List<ItemStack> itemStacksWithLore = new ArrayList<>();
        ItemStack[] contents = inventory.getContents();
        for (ItemStack itemStack : contents) {
            if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
                itemStacksWithLore.add(itemStack);
            }
        }
        return itemStacksWithLore;
    }
}
