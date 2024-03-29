package net.dominionserver.dominiontweaks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DominionTweaks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveResource("config.yml",false);
        //Boolean BlockPlayerFireworkDamage = getConfig().getBoolean("BlockPlayerFireworkDamage");
        //Boolean BlockNametaggedFireworkDamage = getConfig().getBoolean("BlockNametaggedFireworkDamage");
        //Boolean ChannelingDuringRain = getConfig().getBoolean("ChannelingDuringRain");
        //Float ChannelingChance = getConfig().getFloatList(ChannelingChance);
        //Boolean BlockSkeletonItemFrames = getConfig().getBoolean("BlockSkeletonItemFrames");
        //Boolean BlockSkeletonArmorStand = getConfig().getBoolean("BlockSkeletonArmorStand");
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        if (this.getConfig().getBoolean("BlockSkeletonPaintings")){
            getServer().getPluginManager().registerEvents(new HangingBreakListener(this), this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
