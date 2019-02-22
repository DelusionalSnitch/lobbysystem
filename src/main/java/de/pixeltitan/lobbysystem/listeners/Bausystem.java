/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class Bausystem implements Listener {
    @EventHandler
    public void bukkitfill(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if(Main.getListManager().getBuildPlayers().contains(p) && (p.hasPermission("pt.build"))) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }
    @EventHandler
    public void bukkitempty(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        if(Main.getListManager().getBuildPlayers().contains(p) && (p.hasPermission("pt.build"))) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(Main.getListManager().getBuildPlayers().contains(p) && (p.hasPermission("pt.build"))) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(Main.getListManager().getBuildPlayers().contains(p) && (p.hasPermission("pt.build"))) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }
}
