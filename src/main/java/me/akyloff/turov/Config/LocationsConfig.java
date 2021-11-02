package me.akyloff.turov.Config;

import java.util.*;
import org.bukkit.*;
import java.util.stream.*;
import me.akyloff.turov.*;
import org.bukkit.entity.*;
import java.util.function.*;
import me.akyloff.turov.Utils.*;
import org.bukkit.configuration.*;

public class LocationsConfig {

    private Map<String, Location> locationMap = new HashMap<>();
    private Main main;

    public LocationsConfig(Main main) {
        this.main = main;
        reload();
    }

    public void reload() {
        ConfigurationSection section = main.getConfig().getConfigurationSection("Locations");
        locationMap = section.getKeys(false).stream().collect(Collectors.toMap(Function.identity(), s-> LocationUtil.strToLoc(section.getString(s))));
    }

    public void setLocation(String path, Location location) {
        if (locationMap.containsKey(path)) {
            locationMap.replace(path, location);
        } else {
            locationMap.put(path, location);
        }
        save();
    }

    public void save() {
        main.getConfig().set("Locations", null);
        for (Map.Entry<String, Location> entry : locationMap.entrySet()) {
            main.getConfig().set("Locations." + entry.getKey(), LocationUtil.locToStr(entry.getValue()));
        }
        main.saveConfig();
    }

    public Location getLocation(String path, Player player) {
        return locationMap.getOrDefault(path, player.getLocation());
    }

}
