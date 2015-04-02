package co.tdude.obliterate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tristan on 2015-04-02.
 */
public class Main extends JavaPlugin {

    Map<UUID, Long> users;
    String cooldownmessage;
    Long cooldown;

    @Override
    public void onEnable() {
        getLogger().info("Obliterate by Tristan enabled!");
        users = new ConcurrentHashMap<UUID, Long>();
        cooldown = getConfig().getLong("kill-cooldown");
        cooldownmessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("cooldown-message"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                if (users.get(((Player) sender).getUniqueId()) == null) {
                    ((Player) sender).setHealth(0);
                    users.put(((Player) sender).getUniqueId(), System.currentTimeMillis());
                } else if (users.get(((Player) sender).getUniqueId()) != null && users.get(((Player) sender).getUniqueId()) + (cooldown * 1000) <= System.currentTimeMillis()) {
                    ((Player) sender).setHealth(0);
                } else {
                    sender.sendMessage(cooldownmessage);
                }
                return true;
            } else {
                sender.sendMessage("You are not a player.");
                return true;
            }
        }
        return false;
    }
}
