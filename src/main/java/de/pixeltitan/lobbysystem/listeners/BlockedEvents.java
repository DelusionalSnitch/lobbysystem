/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BlockedEvents implements Listener {
    //Erstellt von Julian, deaktiviert alle unnütze Events.

    //Errungenschaften deaktiviert
    @EventHandler
    public void onAchievmentrecieve(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    //Gesammer Schaden aus
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e)
    {
        if ((e.getEntity() instanceof Player)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Entity Damager = e.getDamager();
        if
        ((Damager instanceof Player)) {
            e.setCancelled(true);


        }
    }
    @EventHandler
    public void onMobGrief(EntityExplodeEvent e) {
        e.blockList().clear();
    }

    //Nix aufheben, nix droppen
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    //Kein ausbreiten von Feuer
    @EventHandler
    public void onBlockSpread(org.bukkit.event.block.BlockSpreadEvent event) {
        if (event.getNewState().getType() == Material.FIRE) {
            event.setCancelled(true);
        }
    }

    //Hunger und essen aus
    @EventHandler
    public void onFeedr(PlayerItemConsumeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
    //Crafting deaktiviert
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        e.setCancelled(true);
    }
    //Eis schmelzen deaktiviert
    @EventHandler
    public void onSmelt(BlockFadeEvent e) {
        Block block = e.getBlock();
        if(block.getType() == Material.ICE) {
            e.setCancelled(true);
        }
    }
    //Blätter verschwinden deaktiviert
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }
    //Wetter aus
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    //Mobs und Tiere deaktiviert
    @EventHandler
    public void onEntityspawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        e.setCancelled(true);
    }
}
