package net.dominionserver.dominiontweaks.Commands;
import net.dominionserver.dominiontweaks.DominionTweaks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("dominion.reload")) {
            DominionTweaks.instance._reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded Config and Listeners"));
            return true;
        }
        return false;
    }
}
