package me.freakzboy.main;

import net.minecraft.server.v1_15_R1.PacketPlayOutUpdateHealth;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthBars extends JavaPlugin {

    int scale = 40;

    @Override
    public void onEnable() {
        System.out.println("Health-Bars: plugin enabled!");

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {

                    p.setHealthScale(scale);

                    if(p.getHealth() >= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    }

                    float percentage = (int) (p.getHealth()/p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * scale;

                    new ActionBar(ChatColor.RED + "❤ " + (int) p.getHealth() + "/" + (int) p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()).send(p);

                    if(percentage == 0) {
                        continue;
                    }

                    PacketPlayOutUpdateHealth packet = new PacketPlayOutUpdateHealth(percentage, p.getFoodLevel(), p.getSaturation());
                    PlayerConnection pc = ((CraftPlayer) p).getHandle().playerConnection;
                    pc.sendPacket(packet);
                }
            }
        }.runTaskTimer(this, 0, 1);
    }

}
