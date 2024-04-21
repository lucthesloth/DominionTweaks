package net.dominionserver.dominiontweaks;

import net.dominionserver.dominiontweaks.Commands.NetherTunnel;
import net.dominionserver.dominiontweaks.Listeners.DamageListener;
import net.dominionserver.dominiontweaks.Listeners.HangingBreakListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class DominionTweaks extends JavaPlugin {
    public static DominionTweaks instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        if (this.getConfig().getBoolean("BlockSkeletonPaintings", true)){
            getServer().getPluginManager().registerEvents(new HangingBreakListener(), this);
        }
    }
    private void reloadListeners(){
        HandlerList.unregisterAll(this);
        registerListeners();
    }

    public void _reloadConfig(){
        this.reloadConfig();
        this.reloadListeners();
    }

    public void registerCommands(){
        Objects.requireNonNull(getCommand("nethertunnel")).setExecutor(new NetherTunnel());
    }
}
