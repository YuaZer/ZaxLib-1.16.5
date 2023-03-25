package io.github.yuazer.zaxlib.Utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.UUID;

public class PlayerUtil {

    public static boolean hasItem(Player player, String itemName, int amount, boolean... all) {
        int count = 0;
        int max = all.length > 0 && all[0] ? player.getInventory().getSize() : 36;
        for (int i = 0; i < max; ++i) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().equals(itemName)) {
                count += itemStack.getAmount();
                if (count >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasItemOriginal(Player player, String originalName, int amount, boolean... all) {
        int count = 0;
        int max = all.length > 0 && all[0] ? player.getInventory().getSize() : 36;
        for (int i = 0; i < max; ++i) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.getType().name().equalsIgnoreCase(originalName)) {
                count += itemStack.getAmount();
                if (count >= amount) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 根据Player获取ServerPlayerEntity
     * */
    public static ServerPlayerEntity getServerPlayerEntity(Player player) {
        UUID playerUUID = player.getUniqueId();
        return playerUUID == null ? null : ServerLifecycleHooks.getCurrentServer().func_184103_al().func_177451_a(playerUUID);
    }
    public static boolean inArea(Player player, Location loc1, Location loc2) {
        Location location = player.getLocation();
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        return location.getBlockX() >= minX && location.getBlockX() <= maxX &&
                location.getBlockY() >= minY && location.getBlockY() <= maxY &&
                location.getBlockZ() >= minZ && location.getBlockZ() <= maxZ;
    }

    public static void takeOriginalItem(Player player, String originalName, int amount, boolean... all) {
        if (hasItemOriginal(player, originalName, amount)) {
            int need = amount;
            int max = all.length > 0 && all[0] ? player.getInventory().getSize() : 36;

            for (int i = 0; i < max && need > 0; ++i) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.getType().name().equals(originalName)) {
                    int itemAmount = itemStack.getAmount();
                    int take = itemAmount > need ? itemAmount - need : itemAmount;
                    if (take == itemAmount) {
                        itemStack.setType(Material.AIR);
                    } else {
                        itemStack.setAmount(take);
                    }

                    player.getInventory().setItem(i, itemStack);
                    need -= take;
                }else{
                }
            }
        }
    }

    public static void takeItem(Player player, String itemName, int amount, boolean... all) {
        if (hasItem(player, itemName, amount)) {
            int need = amount;
            int max = all.length > 0 && all[0] ? player.getInventory().getSize() : 36;
            for (int i = 0; i < max && need > 0; ++i) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().equals(itemName)) {
                    int itemAmount = itemStack.getAmount();
                    int take = itemAmount > need ? itemAmount - need : itemAmount;
                    if (take == itemAmount) {
                        itemStack.setType(Material.AIR);
                    } else {
                        itemStack.setAmount(take);
                    }
                    player.getInventory().setItem(i, itemStack);
                    need -= take;
                }
            }
        }
    }

    public static boolean hasNullSlot(Player player) {
        int maxSlot = 35;
        boolean hasNull = false;
        for (int count = 0; count <= maxSlot; count++) {
            org.bukkit.inventory.ItemStack itemStack = player.getInventory().getItem(count);
            if (itemStack == null || itemStack.getType().isAir()) {
                hasNull = true;
            }
        }
        return hasNull;
    }
    public static void takeNameItem(Player player, String name, int amount) {
        int maxSlot = 35;
        int need = amount;
        for (int count = 0; count <= maxSlot && need > 0; count++) {
            org.bukkit.inventory.ItemStack itemStack = player.getInventory().getItem(count);
            if (!itemStack.getType().isAir() && itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().equals(name)) {
                int itemAmount = itemStack.getAmount();
                int take = itemAmount > need ? itemAmount - need : itemAmount;
                if (take == itemAmount) {
                    itemStack.setType(Material.AIR);
                } else {
                    itemStack.setAmount(take);
                }
                player.getInventory().setItem(count, itemStack);
                need -= take;
            } else {
                player.sendMessage("您的背包没有足够物品！");
                return;
            }
        }
    }
    public static void saveInventoryToFile(Inventory inventory, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(inventory);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static Inventory loadInventoryFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Inventory inventory = (Inventory) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return inventory;
    }
}