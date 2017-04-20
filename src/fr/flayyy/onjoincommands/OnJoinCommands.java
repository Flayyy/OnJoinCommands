package fr.flayyy.onjoincommands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class OnJoinCommands extends JavaPlugin implements Listener {
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("onjoincommands", new ArrayList() {

        });
        config.options().copyDefaults(true);

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        List<String> onJoinCommands = config.getStringList("onjoincommands");
        String playerName = player.getName();
        String worldName = player.getWorld().getName();

        for(String command : onJoinCommands) {
            command = command.replaceAll("%player%", playerName).replaceAll("%world%", worldName);
            player.performCommand(command);
        }
    }
}
