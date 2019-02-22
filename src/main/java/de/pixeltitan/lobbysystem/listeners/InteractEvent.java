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
import de.pixeltitan.lobbysystem.utils.nick.NickUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {
     @EventHandler
    public void onInteract(PlayerInteractEvent e) {
         Player p = e.getPlayer();
             ItemStack item = p.getItemInHand();

             if (e.getAction() == Action.RIGHT_CLICK_BLOCK || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
                 if(item == null || !item.hasItemMeta()) return;

                 if (item.getType() == Material.AIR ||  (!(p.hasPermission("pt.silentlobby"))))
                     return;
                 if (item.getItemMeta().getDisplayName() == "§c§lSilentlobby" && (item.getType() == Material.TNT)) {

                     if(Main.isSilentServer()){
                         LobbyUtils.sendPlayerToServer(p, Main.getLobbyserver());
                         p.sendMessage(Main.getPrefix() + "§aDu wirst nun mit der §eLobby §averbunden!");
                     }else {
                         LobbyUtils.sendPlayerToServer(p, Main.getSilentserver());
                         p.sendMessage(Main.getPrefix() + "§aDu wirst nun mit der §eSilent-Lobby §averbunden!");
                     }

                 }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lSpieler verstecken") && (item.getType().equals(Material.BLAZE_ROD))){
                     p.openInventory(HideGui.getHideinv());
                 }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lNick") && (item.getType().equals(Material.NAME_TAG))){
                     NickUtils.nick(p);
                     if(NickUtils.isNicked(p)){
                         p.sendMessage(Main.getPrefix() + "§eDu wurdest §agenickt§e: §6" + p.getName());
                     }else {
                         p.sendMessage(Main.getPrefix() + "§eDu wurdest §centnickt§e!");
                     }

                 }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§7§lNavigator") && (item.getType().equals(Material.COMPASS))){
                     if(p.isSneaking()){
                         p.openInventory(NavigatorGui.getNavSetInv());
                     }else {
                         p.openInventory(NavigatorGui.getNavinv());
                     }
                 }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§f§lLobbyswitcher") && (item.getType().equals(Material.WATCH))){
                     p.openInventory(LobbyswitcherGui.getSwitcherInv(p));
                 }

             }


     }
}
