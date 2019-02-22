/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian                                                                                
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;

public class FlightListener implements Listener {
    private ArrayList<Player> doublejump = new ArrayList<Player>();
    @EventHandler
    public void onFly(PlayerToggleFlightEvent e){
        final Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.CREATIVE)) return;

        e.setCancelled(true);

        p.setAllowFlight(false);
        p.setFlying(false);
        if(!doublejump.contains(p)){
            p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
            doublejump.add(p);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                public void run() {
                    doublejump.remove(p);
                }
            }, 100);
        }
    }
}
