package me.akyloff.turov.System.Spawn;

import org.bukkit.*;
import me.akyloff.turov.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;

public class SetSpawnCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("core.commands.setspawn")) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.noPermissions").replace("&", "§"));
                return true;
            }
            Location location = ((Player)sender).getLocation();
            Main.getInstance().getLocationsConfig().setLocation("Spawn", location);
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.setSpawn").replace("&", "§"));
            return true;
        }
        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.commandOnlyForPlayers").replace("&", "§"));
        return true;
    }

}
