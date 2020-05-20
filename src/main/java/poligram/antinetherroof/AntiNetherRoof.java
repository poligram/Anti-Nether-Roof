package poligram.antinetherroof;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiNetherRoof extends JavaPlugin {
    private static int id;
    @Override
    public void onEnable() {
        // Plugin startup logic
        id = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                stopPlayersOnNetherRoof();
            }}, 0, 100);
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(id);
    }

    private static void stopPlayersOnNetherRoof() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getWorld().getEnvironment().getId() == -1) {
                if (p.getLocation().getY() >= 128) {
                    Location l = p.getLocation();
                    l.setY(121);
                    p.teleport(l);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lHey! No going above the nether roof!"));
                    System.out.println("Player " + p.getName() + " has gone above the nether roof! Teleporting him down");
                }
            }
        }
    }
}
