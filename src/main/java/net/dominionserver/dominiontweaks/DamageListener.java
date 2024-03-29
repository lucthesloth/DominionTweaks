package net.dominionserver.dominiontweaks;

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
        Entity directEntity = event.getDamageSource().getDirectEntity();
        Entity causingEntity = event.getDamageSource().getCausingEntity();
        Entity entity = event.getEntity();

        Bukkit.broadcastMessage("DIRECT ENTITY");
        Bukkit.broadcastMessage(directEntity.toString());
        Bukkit.broadcastMessage("CAUSING ENTITY");
        Bukkit.broadcastMessage(causingEntity.toString());
        Bukkit.broadcastMessage("ENTITY");
        Bukkit.broadcastMessage(entity.toString());

        if (directEntity.getType() == EntityType.FIREWORK) {
            if (entity.getType() == EntityType.PLAYER && plugin.getConfig().getBoolean("BlockPlayerFireworkDamage")) {
                event.setCancelled(true);
                Bukkit.broadcastMessage("NO BOOM!");
                Bukkit.broadcastMessage("NO BOOM!");
                Bukkit.broadcastMessage("NO BOOM!");
            }
            if (entity.getCustomName() != null && plugin.getConfig().getBoolean("BlockNametaggedFireworkDamage")) {
                event.setCancelled(true);
                Bukkit.broadcastMessage("SAFE PETS!");
                Bukkit.broadcastMessage("SAFE PETS!");
                Bukkit.broadcastMessage("SAFE PETS!");
            }
        }
        if (entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.GLOW_ITEM_FRAME) {
            if (plugin.getConfig().getBoolean("BlockSkeletonItemFrames") && causingEntity.getType() == EntityType.SKELETON) {
                event.setCancelled(true);
                Bukkit.broadcastMessage("BAD SKELETON!");
                Bukkit.broadcastMessage("SAFE MAPS!");
                Bukkit.broadcastMessage("SAFE MAPS!");
            }
        }
        if (entity.getType() == EntityType.ARMOR_STAND && causingEntity.getType() == EntityType.SKELETON && plugin.getConfig().getBoolean("BlockSkeletonArmorStand")) {
            event.setCancelled(true);
            Bukkit.broadcastMessage("BAD SKELETON!");
            Bukkit.broadcastMessage("SAFE ARMOR STANDS!");
            Bukkit.broadcastMessage("SAFE ARMOR STANDS!");
        }
    }
}
