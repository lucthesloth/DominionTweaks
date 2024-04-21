package net.dominionserver.dominiontweaks.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
}
