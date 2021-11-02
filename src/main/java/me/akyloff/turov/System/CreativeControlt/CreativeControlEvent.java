package me.akyloff.turov.System.CreativeControlt;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.block.*;
import me.akyloff.turov.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;

public class CreativeControlEvent implements Listener {

    private final Main plugin;

    public CreativeControlEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void InteractEvent(PlayerInteractEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.CreativeControlBlocks")) {
            if (!event.getPlayer().hasPermission("core.creativecontrol.bypass")) {
                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                    Block clickedBlock = event.getClickedBlock();
                    if (event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
                        return;
                    }

                    if (!this.plugin.getWhiteListBlocks().contains(clickedBlock.getType())) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.getInstance().getConfig().getString("Messages.creativeControlBlocks").replace("&", "§"));
                    }
                }

            }
        }
    }

    @EventHandler
    public void CommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.CreativeControlCommand")) {
            if (!event.getPlayer().hasPermission("core.creativecontrol.bypass")) {
                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                    String message = event.getMessage();
                    StringBuilder command = new StringBuilder(6);

                    for(int i = 1; i < message.length(); ++i) {
                        char character = message.charAt(i);
                        if (character == ' ') {
                            break;
                        }

                        command.append(character);
                    }

                    if (this.plugin.getBlackListCreativeCommand().contains(command.toString())) {
                        event.getPlayer().sendMessage(Main.getInstance().getConfig().getString("Messages.creativeControlCommand").replace("&", "§"));
                        event.setCancelled(true);
                    }
                }

            }
        }
    }

    @EventHandler
    public void InventoryCreativeEvent(InventoryCreativeEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.CreativeControlCommand")) {
            if (!event.getWhoClicked().hasPermission("core.creativecontrol.bypass")) {
                if (!this.plugin.getCreativeWhiteList().contains(event.getCursor().getType())) {
                    event.getWhoClicked().sendMessage(Main.getInstance().getConfig().getString("Messages.blackblock").replace("&", "§"));
                    event.setCancelled(true);
                }

            }
        }
    }

    @EventHandler
    public void DropItemEvent(PlayerDropItemEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.PutBlocks")) {
            if (!event.getPlayer().hasPermission("core.creativecontrol.bypass")) {
                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                    event.getPlayer().sendMessage(Main.getInstance().getConfig().getString("Messages.dropBlocks").replace("&", "§"));
                    event.setCancelled(true);
                }

            }
        }
    }

    @EventHandler
    public void PickupItemEvent(EntityPickupItemEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Function.DropBlocks")) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player)event.getEntity();
                if (!player.hasPermission("core.creativecontrol.bypass")) {
                    if (player.getGameMode() == GameMode.CREATIVE) {
                        player.sendMessage(Main.getInstance().getConfig().getString("Messages.putBlocks").replace("&", "§"));
                        event.setCancelled(true);
                    }

                }
            }
        }
    }

}
