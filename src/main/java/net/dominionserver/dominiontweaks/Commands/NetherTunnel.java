package net.dominionserver.dominiontweaks.Commands;

import net.dominionserver.dominiontweaks.Utils.NetherTunnelUtils;
import net.dominionserver.dominiontweaks.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NetherTunnel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player p) {
            if (args.length == 0 || args[0].equalsIgnoreCase("here")) {
                Utils.sendResponseWithPrefix(p, NetherTunnelUtils.getClosestTunnel(p.getWorld().getName().contains("nether") ?
                        p.getLocation() : p.getLocation().multiply(0.125)));
                return true;
            }
        }
        if (args.length == 3){
            try {
                int x,z;
                x = Integer.parseInt(args[1]);
                z = Integer.parseInt(args[2]);
                if (args[0].equalsIgnoreCase("o")){
                    x = x/8;
                    z = z/8;
                }
                Utils.sendResponseWithPrefix(commandSender, NetherTunnelUtils.getClosestTunnel(x,z));
                return true;
            } catch (NumberFormatException e){
                Utils.sendResponseWithPrefix(commandSender, "§cMalformed coordinates, please make sure they are integers such as 42");
                return false;
            }
        }
        Utils.sendResponseWithPrefix(commandSender, "§cNether Tunnel Utilities.");
        Utils.sendResponseWithPrefix(commandSender, "Usage: /nethertunnel WorldName X Z");
        Utils.sendResponseWithPrefix(commandSender, "WorldName can be either \"o\" for overworld or \"n\" for nether.");
        Utils.sendResponseWithPrefix(commandSender, "Example: /nethertunnel o 1234 -4242");
        Utils.sendResponseWithPrefix(commandSender, "Example 2: /nethertunnel here or /nethertunel will give you the response based on where you are standing.");
        return false;
    }
}
