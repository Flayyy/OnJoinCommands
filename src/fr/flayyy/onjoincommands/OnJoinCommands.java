package fr.flayyy.onjoincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

/**
 * @author Flayyy
 * @since 1.11.2-R0.1
 */
public class OnJoinCommands extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    public void onEnable() {
        // Register and load configuration file
        if (!getDataFolder().exists()) {
            getLogger().info("Creating OnJoinCommands folder");
            if (!getDataFolder().mkdirs()) {
                getLogger().warning("Unable to create OnJoinCommand folder");
            } else {
                getLogger().info("Successfully created");
            }
        }

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getLogger().info("config.yml was not found, creating!");
            saveDefaultConfig();
        } else {
            getLogger().info("Loading config.yml");
        }

        getCommand("ojcreload").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }


    /* ==============================================================================
     * Events
     * ==============================================================================*/
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        performPlayerCommands(event, "join");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        performPlayerCommands(event, "quit");
    }


    /* ==============================================================================
     * Utils
     * ==============================================================================*/
    /**
     * Perform commands read from the config.yml file
     *
     * @param event     the PlayerEvent raised
     * @param eventType the event type to find the corresponding entry in the config.yml file
     */
    private void performPlayerCommands(PlayerEvent event, String eventType) {
        Player player = event.getPlayer();
        List<String> onJoinCommands = getConfig().getStringList(eventType);
        String playerName = player.getName();
        String worldName = player.getWorld().getName();

        for (String command : onJoinCommands) {
            command = command.replaceAll("%player%", playerName).replaceAll("%world%", worldName);
            player.performCommand(command);
        }
    }

    /* ==============================================================================
     * Commands
     * ==============================================================================*/
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("ojc.reload")) {
                player.sendMessage("Sadly, you are not allowed to do this.");
                return false;
            }
        }

        getLogger().info("Reloading OnCommandJoin configuration");
        reloadConfig();
        getLogger().info("Successfully reloaded OnCommandJoin configuration");
        return true;
    }
}
