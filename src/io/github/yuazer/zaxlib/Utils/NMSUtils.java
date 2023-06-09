package io.github.yuazer.zaxlib.Utils;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NMSUtils {
    private static NMSUtils utils;

    public static NMSUtils getUtils() {
        return utils;
    }

    public static World bkToNmsWorld(org.bukkit.World world) {
        return ((CraftWorld) world).getHandle();
    }

    public static ItemStack bkToNmsItemStack(org.bukkit.inventory.ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }

    public static org.bukkit.inventory.ItemStack nmsToBkItemStack(ItemStack itemStack) {
        return CraftItemStack.asBukkitCopy(itemStack);
    }

    public static net.minecraft.entity.Entity bkToNmsEntity(Entity entity) {
        net.minecraft.entity.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        return nmsEntity;
    }

    public static org.bukkit.World nmsToBkWorld(World world) {
        return world.getWorld();
    }
}
