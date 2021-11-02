package me.akyloff.turov.System.Spawn;

import me.akyloff.turov.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            player.teleport(Main.getInstance().getLocationsConfig().getLocation("Spawn", player));
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.spawnTeleport").replace("&", "§"));
            return true;
        }
        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.commandOnlyForPlayers").replace("&", "§"));
        return true;
    }

}
