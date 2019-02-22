/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import de.pixeltitan.lobbysystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

public class WarpUtils {

    private static File file = new File(Main.getInstance().getDataFolder() + File.separator, "warps.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setWarp(Location loc, String name){
        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", loc.getX());
        cfg.set(name + ".y", loc.getY());
        cfg.set(name + ".z", loc.getZ());
        cfg.set(name + ".yaw", loc.getYaw());
        cfg.set(name + ".pitch", loc.getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Location getWarp(String name){
        if(cfg.isSet(name + ".world")){
            Float yaw = Float.parseFloat(cfg.getString(name + ".yaw"));
            Float pitch = Float.parseFloat(cfg.getString(name + ".pitch"));
            Location loc = new Location(Bukkit.getWorld(cfg.getString(name + ".world")), cfg.getDouble(name + ".x"), cfg.getDouble(name + ".y"), cfg.getDouble(name + ".z"), yaw, pitch);
            return loc;
        }
        return null;
    }
    public static void tpPlayer(final String warp, final Player p){

        p.closeInventory();
        p.setVelocity(new Vector(0, 2, 0));
        p.sendMessage(Main.getPrefix() + "§aDu wirst zum Warp §6" + warp + " §ateleportiert!");
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                p.teleport(WarpUtils.getWarp(warp));
                p.sendMessage(Main.getPrefix() + "§aDu wurdest teleportiert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            }
        }, 19);
    }

}
