package me.akyloff.turov.System;

import org.bukkit.event.*;
import me.akyloff.turov.*;
import org.bukkit.event.player.*;

public class BlockCommandEvent implements Listener {

    private Main plugin;

    public BlockCommandEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void CommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.BlackListCommand")) {
            if (!event.getPlayer().hasPermission("core.blackcommand.bypass")) {
                String message = event.getMessage();
                StringBuilder command = new StringBuilder(6);

                for(int i = 1; i < message.length(); ++i) {
                    char character = message.charAt(i);
                    if (character == ' ') {
                        break;
                    }

                    command.append(character);
                    if (this.plugin.getBlackListCommand().contains(command.toString())) {
                        event.getPlayer().sendMessage(Main.getInstance().getConfig().getString("Messages.blockCommand").replace("&", "§"));
                        event.setCancelled(true);
                    }
                }

            }
        }
    }

}
