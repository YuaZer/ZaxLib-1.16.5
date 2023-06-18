package io.github.yuazer.zaxlib.RunnableUtils;

//导入相关的类
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

//创建一个BukkitRunnableManager类
public class BukkitRunnableManager {

    //创建一个HashMap对象，用来存储String和BukkitRunnable的对应关系
    private HashMap<String, BukkitRunnable> runnables;

    //创建一个JavaPlugin对象，用来获取插件实例
    private JavaPlugin plugin;

    //创建一个构造方法，传入插件实例
    public BukkitRunnableManager(JavaPlugin plugin) {
        //初始化HashMap对象
        runnables = new HashMap<>();
        //赋值插件实例
        this.plugin = plugin;
    }

    //创建一个方法，用来添加一个String和BukkitRunnable的对应关系
    public void addRunnable(String name, BukkitRunnable runnable) {
        //将name和runnable放入HashMap中
        runnables.put(name, runnable);
    }

    //创建一个方法，用来移除一个String和BukkitRunnable的对应关系
    public void removeRunnable(String name) {
        //从HashMap中移除name对应的runnable
        runnables.remove(name);
    }

    //创建一个方法，用来开启指定的BukkitRunnable
    public void startRunnable(String name, long delay, long period) {
        //从HashMap中获取name对应的runnable
        BukkitRunnable runnable = runnables.get(name);
        //判断runnable是否存在
        if (runnable != null) {
            //如果存在，调用runTaskTimer方法，传入插件实例，延迟时间和周期时间
            runnable.runTaskTimerAsynchronously(plugin, delay, period);
        }
    }

    //创建一个方法，用来关闭指定的BukkitRunnable
    public void stopRunnable(String name) {
        //从HashMap中获取name对应的runnable
        BukkitRunnable runnable = runnables.get(name);
        //判断runnable是否存在
        if (runnable != null) {
            //如果存在，调用cancel方法，取消任务
            runnable.cancel();
        }
    }
}
