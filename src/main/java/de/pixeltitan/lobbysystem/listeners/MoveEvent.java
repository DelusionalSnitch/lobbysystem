/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.utils.WarpUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e)  {
        Player p = e.getPlayer();

        if(p.getGameMode() != GameMode.CREATIVE && p.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.AIR) && !p.isFlying()){
            p.setAllowFlight(true);
        }

        if(p.getLocation().add(0, -1, 0).getBlock().getType().equals(Material.GOLD_PLATE) || p.getLocation().getBlock().getType().equals(Material.GOLD_PLATE)){
            Vector v = p.getLocation().getDirection().multiply(2).setY(1);
            p.setVelocity(v);
            p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 1.0F, 1.0F);
        }
        if(p.getLocation().getY() < 10) {
            p.teleport(WarpUtils.getWarp("Spawn"));
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
        }

    }

}
