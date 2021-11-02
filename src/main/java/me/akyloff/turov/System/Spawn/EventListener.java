package me.akyloff.turov.System.Spawn;

import me.akyloff.turov.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().teleport(Main.getInstance().getLocationsConfig().getLocation("Spawn", event.getPlayer()));
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(Main.getInstance().getLocationsConfig().getLocation("Spawn", event.getPlayer()));
    }

}
