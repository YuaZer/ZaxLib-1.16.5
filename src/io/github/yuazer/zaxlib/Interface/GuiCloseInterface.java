package io.github.yuazer.zaxlib.Interface;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface GuiCloseInterface {
    void execute(InventoryCloseEvent var1);
}
