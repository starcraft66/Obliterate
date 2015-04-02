package co.tdude.obliterate;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Tristan on 2015-04-02.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Obliterate by Tristan enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                ((Player) sender).setHealth(0);
                return true;
            } else {
                sender.sendMessage("You are not a player.");
                return true;
            }
        }
        return false;
    }
}
