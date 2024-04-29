package net.dominionserver.dominiontweaks.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Objects;

public class Utils {
    public static void sendResponseWithPrefix(Player player, String message){
        player.sendMessage(String.format("§3[§6Dominion§3]§8 >§r %s", message));
    }
    public static void sendResponseWithPrefix(CommandSender s, String message){
        s.sendMessage(String.format("§3[§6Dominion§3]§8 >§r %s", message));
    }
    public static int distSquared(int x, int z, int nx, int nz){
        return (int)((Math.round(Math.sqrt(Math.pow(x - nx, 2) + Math.pow(z - nz, 2)))));
    }

    public static Location getSurfaceLocationWithinRadius(Location l, int r){
        return getSurfaceLocationWithinRadius(l.getBlockX(), l.getBlockZ(), Objects.requireNonNull(l.getWorld()), r >= 0 ? r : 16);
    }
    public static Location getSurfaceLocationWithinRadius(int x, int z, World w, int r){
        int randX = (int) ((Math.random() * (r * 2)) - r) + x;
        int randZ = (int) ((Math.random() * (r * 2)) - r) + z;
        int y = w.getMaxHeight();
        Location temp = new Location(w, randX, y, randZ);
        // Bad source -> return w.getHighestBlockAt(randX, randZ).getLocation();
        while(temp.getBlock().getBlockData().getMaterial() != Material.AIR)
            temp.add(0, -1, 0);
        return temp.add(0, 1, 0);
    }
}
