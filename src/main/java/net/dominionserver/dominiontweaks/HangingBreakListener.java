package net.dominionserver.dominiontweaks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;


public class HangingBreakListener implements Listener {
    private DominionTweaks plugin;



    public HangingBreakListener(DominionTweaks instance){
        this.plugin = instance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        Entity cause = event.getRemover();
        if (cause.getType() == EntityType.SKELETON) {
            event.setCancelled(true);
            Bukkit.broadcastMessage("SAFE PAINTINGS!");
            Bukkit.broadcastMessage("SAFE PAINTINGS!");
            Bukkit.broadcastMessage("SAFE PAINTINGS!");
        }

    }
}
