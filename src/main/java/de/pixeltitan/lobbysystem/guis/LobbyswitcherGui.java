/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian                                                                                
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.guis;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.CloudNetwork;
import de.dytanic.cloudnet.lib.server.ServerGroup;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeltitan.lobbysystem.main.Main;
import de.pixeltitan.lobbysystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LobbyswitcherGui {
    private static Inventory switcherInv = Bukkit.createInventory(null, 1*9, "§e§l>> §r§fLobbyswitcher");

    public static Inventory getSwitcherInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 1*9, switcherInv.getName());

        //TODO: Lobby Collection Sortieren, damit Lobby-1, Lobby-2 etc. und nicht Lobby-3, Lobby-1, Lobby-2 oder so.


          for(int i = 0; i < CloudAPI.getInstance().getServers(Main.getLobbygroup()).size(); i++) {
           for(ServerInfo server : CloudAPI.getInstance().getServers(Main.getLobbygroup())) {
               int serverid = i+1;
               String servername = Main.getLobbygroup() + "-" + serverid;
               int online = CloudAPI.getInstance().getServerInfo(servername).getOnlineCount();
               ItemBuilder builder = new ItemBuilder(Material.NETHER_STAR, "§8➞ §a" + servername, online);
                builder.addItemFlag(ItemFlag.HIDE_ENCHANTS);

               List<String> lore = new ArrayList<String>();
                ServerInfo serverInfo = CloudAPI.getInstance().getServerInfo(servername);
               if (serverInfo.getPlayers().contains(p.getName())) {
                   builder.enchant(Enchantment.DURABILITY, 10);
                   lore.add("§1");
                   lore.add("§7Online Spieler");
                   lore.add("§8➥ §e" + online);
                   lore.add("§2");
                   lore.add("§7Server Status");
                   lore.add("§8➥ §e" + serverInfo.getServerState());
                   lore.add("§3");
                   lore.add("§aDu bist bereits auf dieser Lobby§8!");
               } else {
                   lore.add("§1");
                   lore.add("§7Online Spieler");
                   lore.add("§8➥ §e" + online);
                   lore.add("§2");
                   lore.add("§7Server Status");
                   lore.add("§8➥ §e" + serverInfo.getServerState());
                   lore.add("§3");
                   lore.add("§aKlicke zum beitreten§8!");
               }

               builder.setLore(lore);
               inv.setItem(i, builder.build());
           }
        }

        return inv;
    }

    public static void setSwitcherInv(Inventory switcherinv) {
        LobbyswitcherGui.switcherInv = switcherinv;
    }
}
