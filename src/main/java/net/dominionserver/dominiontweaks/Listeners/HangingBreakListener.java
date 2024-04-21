package net.dominionserver.dominiontweaks.Listeners;

import net.dominionserver.dominiontweaks.DominionTweaks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;


public class HangingBreakListener implements Listener {
    public HangingBreakListener(){
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        if (event.getCause() != HangingBreakEvent.RemoveCause.ENTITY) return;

        if (event.getRemover().getType() == EntityType.SKELETON) {
            event.setCancelled(true);
        }

    }
}
