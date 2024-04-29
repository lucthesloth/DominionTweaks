package net.dominionserver.dominiontweaks.Commands.Completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class NetherTunnelCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String c, String[] strings) {
        if (commandSender instanceof Player p){
            if (strings.length <= 1)
                return Stream.of("o", "n", "here").filter(s -> s.startsWith(strings[0])).toList();
            if (!strings[0].equalsIgnoreCase("here"))
                return Collections.singletonList(String.format("%d %d", p.getLocation().getBlockX(), p.getLocation().getBlockZ()));
            if (strings.length == 2)
                return Collections.singletonList(String.format("%d", p.getLocation().getBlockX()));
            if (strings.length == 3)
                return Collections.singletonList(String.format("%d", p.getLocation().getBlockZ()));
        } else {
            if (strings.length <= 1)
                return Stream.of("o", "n").filter(s -> s.startsWith(strings[0])).toList();
        }
        return null;
    }
}
