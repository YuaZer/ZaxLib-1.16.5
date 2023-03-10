package io.github.yuazer.zaxlib.CustomUtils;

import org.bukkit.Nameable;
import org.bukkit.inventory.Inventory;

public class InventoryUtils {
    public static String getInventoryTitle(Inventory inventory) {
        return ((Nameable) inventory.getHolder()).getCustomName();
    }
}
