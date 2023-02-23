package io.github.yuazer.zaxlib.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import io.github.yuazer.zaxlib.Interface.*;
import io.github.yuazer.zaxlib.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GuiModel implements InventoryHolder, Listener {
    private final Inventory inventory;
    private ExecuteInterface executeInterface;
    private GuiCloseInterface guiCloseInterface;
    private boolean closeRemove;
    private boolean listener;

    public GuiModel(String title, int size) {
        this.inventory = Bukkit.createInventory(this, size, title.replace("&", "§"));
        this.closeRemove = true;
    }

    public void setItem(HashMap<Integer, ItemStack> itemMap) {
        Iterator var2 = itemMap.entrySet().iterator();

        while (var2.hasNext()) {
            Entry<Integer, ItemStack> entry = (Entry) var2.next();
            this.setItem((Integer) entry.getKey(), (ItemStack) entry.getValue());
        }

    }

    public void openInventory(Player player) {
        player.openInventory(this.getInventory());
    }

    public void openInventoryAsync(Plugin plugin, Player player) {
        Inventory finalInventory = this.getInventory();
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(plugin, () -> player.openInventory(finalInventory));
            }
        }.runTaskAsynchronously(plugin);
    }

    public boolean closeRemove() {
        return this.closeRemove;
    }

    public void setCloseRemove(boolean remove) {
        this.closeRemove = remove;
    }

    public void setItem(Integer slot, ItemStack itemStack) {
        this.inventory.setItem(slot, itemStack);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setListener(boolean listener) {
        this.registerListener(Main.getInstance());
    }

    public void registerListener(Plugin plugin) {
        if (!this.listener) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }

        this.listener = true;
    }

    public synchronized void unregisterListener(boolean close) {
        if (this.listener) {
            HandlerList.unregisterAll(this);
        }

        if (close) {
            Iterator iterator = this.inventory.getViewers().iterator();

            while (iterator.hasNext()) {
                HumanEntity humanEntity = (HumanEntity) iterator.next();
                if (humanEntity.getOpenInventory().getTopInventory() == this.inventory) {
                    humanEntity.closeInventory();
                }
            }
        }

    }

    public void execute(ExecuteInterface executeInterface) {
        this.executeInterface = executeInterface;
    }

    public void setCloseInterface(GuiCloseInterface guiCloseInterface) {
        this.guiCloseInterface = guiCloseInterface;
    }

    private void onGuiClose(InventoryCloseEvent event) {
        if (this.guiCloseInterface != null) {
            this.guiCloseInterface.execute(event);
        }

        if (this.closeRemove) {
            this.unregisterListener(false);
        }

    }

    private void run(InventoryClickEvent e) {
        if (this.executeInterface != null) {
            this.executeInterface.execute(e);
        }
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() == this) {
            this.run(e);
        }

    }

    @EventHandler
    private void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() == this) {
            this.onGuiClose(e);
        }

    }
}