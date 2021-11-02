package me.akyloff.turov;

import java.io.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;
import me.akyloff.turov.Config.*;
import me.akyloff.turov.System.*;
import org.bukkit.configuration.*;
import com.google.common.collect.*;
import org.bukkit.configuration.file.*;
import me.akyloff.turov.System.Spawn.*;
import me.akyloff.turov.System.CreativeControlt.*;

public class Main extends JavaPlugin {

    private static Main instance;
    private LocationsConfig locationsConfig;
    private ImmutableList<String> blacklistcreativecommand;
    private ImmutableList<Material> WhiteListBlocks;
    private ImmutableList<String> blacklistcommand;
    private ImmutableList<Material> creativewhitelist;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.locationsConfig = new LocationsConfig(this);
        reloadCustomConfig();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new CreativeControlEvent(this), this);
        getServer().getPluginManager().registerEvents(new BlockCommandEvent(this), this);
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("turov").setExecutor(new TurocChmo()); // ez hack
    }

   /* @Override кому он нужен, нахер нам анрегистер листенерс, правда ведь?
    public void onDisable() {} */

    public ImmutableList<Material> getWhiteListBlocks() {
        return WhiteListBlocks;
    }

    public ImmutableList<Material> getCreativeWhiteList() {
        return creativewhitelist;
    }

    public ImmutableList<String> getBlackListCreativeCommand() {
        return blacklistcreativecommand;
    }

    public ImmutableList<String> getBlackListCommand() {
        return blacklistcommand;
    }

    public void reloadCustomConfig() {
        YamlConfiguration config = new YamlConfiguration();
        try{
            config.load(new File(getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            getLogger().severe("Брат, всё наебнулось.");
            ex.printStackTrace();
        } catch (InvalidConfigurationException ex) {
            getLogger().severe("Брат, всё наебнулось. Формат конфига не такой!!!");
            ex.printStackTrace();
        }
        blacklistcommand = ImmutableList.copyOf(config.getStringList("BlackListCommand"));
        this.blacklistcreativecommand = ImmutableList.copyOf(config.getStringList("BlackListCreativeCommands"));
        this.WhiteListBlocks = config.getStringList("WhiteListBlocks").stream().map(blockName -> Material.valueOf(blockName.toUpperCase())).collect(ImmutableList.toImmutableList());
        this.creativewhitelist = config.getStringList("CreativeWhiteList").stream().map(blockName -> Material.valueOf(blockName.toUpperCase())).collect(ImmutableList.toImmutableList());
    }

    public static Main getInstance() {
        return instance;
    }

    public LocationsConfig getLocationsConfig() {
        return locationsConfig;
    }

}
