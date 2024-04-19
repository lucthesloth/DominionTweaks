package net.dominionserver.dominiontweaks.Listeners;

import net.dominionserver.dominiontweaks.DominionTweaks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class DamageListener implements Listener {
    private DominionTweaks plugin;



    public DamageListener(DominionTweaks instance){
        this.plugin = instance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        EntityType attackerType = event.getDamager().getType();

        if (attackerType == EntityType.FIREWORK) {
            if (victim.getType() == EntityType.PLAYER && plugin.getConfig().getBoolean("BlockPlayerFireworkDamage")) {
                event.setCancelled(true);
            }
            if (victim.getCustomName() != null && plugin.getConfig().getBoolean("BlockNametaggedFireworkDamage")) {
                event.setCancelled(true);
            }
        }
        if (victim.getType() == EntityType.ITEM_FRAME || victim.getType() == EntityType.GLOW_ITEM_FRAME) {
            if (plugin.getConfig().getBoolean("BlockSkeletonItemFrames") && attackerType == EntityType.SKELETON) {
                event.setCancelled(true);
            }
        }
        if (victim.getType() == EntityType.ARMOR_STAND && attackerType == EntityType.SKELETON && plugin.getConfig().getBoolean("BlockSkeletonArmorStand")) {
            event.setCancelled(true);
        }
    }
}
