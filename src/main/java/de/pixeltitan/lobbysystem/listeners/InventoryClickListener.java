/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.guis.HideGui;
import de.pixeltitan.lobbysystem.guis.LobbyswitcherGui;
import de.pixeltitan.lobbysystem.guis.NavigatorGui;
import de.pixeltitan.lobbysystem.main.Main;
import de.pixeltitan.lobbysystem.utils.LobbyUtils;
import de.pixeltitan.lobbysystem.utils.WarpUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class InventoryClickListener implements Listener {
    
    @EventHandler
    
    public void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if(!Main.getListManager().getBuildPlayers().contains(p)){
            e.setCancelled(true);
        }

        if(e.getInventory() == null) return;
        if(item == null) return;
        if(item.getType().equals(Material.AIR)) return;

        if(e.getInventory().getName().equals(HideGui.getHideinv().getName())) {
            if(!(item.getType().equals(Material.AIR) || (item.getType() == null))){
                if(item.getItemMeta().getDisplayName().equals(HideGui.allonName)) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(players);
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    p.sendMessage(Main.getPrefix()+"§aAlle Spieler werden angezeigt");
                }else if(item.getItemMeta().getDisplayName().equals(HideGui.allOffName)) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(players);
                        p.playEffect(players.getLocation(), Effect.TILE_DUST, 40);
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    p.sendMessage(Main.getPrefix()+"§cAlle Spieler werden ausgeblendet");
                } else if(item.getItemMeta().getDisplayName().equals(HideGui.onlyVipName)) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        if(!(players.hasPermission("pt.vip"))) {
                            p.hidePlayer(players);
                            p.playEffect(players.getLocation(), Effect.TILE_DUST, 40);
                        }
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    p.sendMessage(Main.getPrefix()+"§5Es werden nur VIPs angezeigt");
                }
            }
        }else if(e.getInventory().getName().equals(NavigatorGui.getNavinv().getName())){

            if(item.getItemMeta().getDisplayName().equals("§b§lSPAWN")){
                WarpUtils.tpPlayer("Spawn", p);
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lSURVIVAL GAMES")){
                WarpUtils.tpPlayer("Survivalgames", p);
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lPIXEL SHOOTER")){
                WarpUtils.tpPlayer("Pixelshooter", p);
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lFLASH")){
                WarpUtils.tpPlayer("Flash", p);
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lMONTAGS MALER")){
                WarpUtils.tpPlayer("Montagsmaler", p);
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lBEDWARS")){
                WarpUtils.tpPlayer("Bedwars", p);
            }

        }else if(e.getInventory().getName().equals(NavigatorGui.getNavSetInv().getName())){

            boolean setted = false;

            if(item.getItemMeta().getDisplayName().equals("§b§lSPAWN")){
                WarpUtils.setWarp(p.getLocation(), "Spawn");
                setted = true;
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lSURVIVAL GAMES")){
                WarpUtils.setWarp(p.getLocation(), "Survivalgames");
                setted = true;
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lPIXEL SHOOTER")){
                WarpUtils.setWarp(p.getLocation(), "Pixelshooter");
                setted = true;
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lFLASH")){
                WarpUtils.setWarp(p.getLocation(), "Flash");
                setted = true;
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lMONTAGS MALER")){
                WarpUtils.setWarp(p.getLocation(), "Montagsmaler");
                setted = true;
            }
            else if(item.getItemMeta().getDisplayName().equals("§b§lBEDWARS")){
                WarpUtils.setWarp(p.getLocation(), "Bedwars");
                setted = true;
            }

            if(setted){
                p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
                p.sendMessage(Main.getPrefix() + "§aDu hast den Warp für " + item.getItemMeta().getDisplayName() + "§r §agesetzt!");
            }
        }else if(e.getInventory().getName().equalsIgnoreCase(LobbyswitcherGui.getSwitcherInv(p).getName())){
            LobbyUtils.sendPlayerToServer(p, item.getItemMeta().getDisplayName().replace("§8➞ §a", ""));
        }
    }
}
