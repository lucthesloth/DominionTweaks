package net.dominionserver.dominiontweaks.Listeners;

import net.dominionserver.dominiontweaks.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySpawnListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onEntitySpawn(EntitySpawnEvent e){
        if (e.getEntityType() != EntityType.WANDERING_TRADER) return;

        if (e.getEntity().getNearbyEntities(50, 50, 50).stream().noneMatch(t -> t instanceof Player && !t.hasPermission("dominion.alt"))){
            List<Player> onlineNonAlts = Bukkit.getOnlinePlayers().stream().filter(t -> !t.hasPermission("dominion.alt")).collect(Collectors.toList());
            if (onlineNonAlts.isEmpty()) return;
            Collections.shuffle(onlineNonAlts);
            e.getEntity().teleport(Utils.getSurfaceLocationWithinRadius(onlineNonAlts.get(0).getLocation(), 50));
        }

    }
}
