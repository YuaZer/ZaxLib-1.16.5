package io.github.yuazer.zaxlib.AsyncUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Function;

public class EventListenerAsync {
    /**
     * 异步监听事件
     *
     * @param plugin        您的插件实例
     * @param listener      事件监听器实例
     */
    public static <T extends Listener> void listen(Plugin plugin, Listener listener) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().registerEvents(listener, plugin);
            }
        }.runTaskAsynchronously(plugin);
    }
}
