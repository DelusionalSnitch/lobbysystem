/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {
    @EventHandler
    public void onCommmand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String fullCmd = e.getMessage().substring(1);
        String cmd = fullCmd.split(" ")[0];
        if(Bukkit.getServer().getHelpMap().getHelpTopic(e.getMessage().split(" ")[0]) == null) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.getPrefix() + "§7Der Befehl §f(§f'§e" + e.getMessage().split(" ")[0] + "§8§f'§f) §7wurde nicht gefunden");
        }
    }
    }

