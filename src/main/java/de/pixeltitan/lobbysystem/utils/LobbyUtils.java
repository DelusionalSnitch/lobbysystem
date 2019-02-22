/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeltitan.lobbysystem.main.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LobbyUtils {
    public static void giveLobbyItems(Player p) {

        //Navigator Item
        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, "§7§lNavigator", 1).build());

        //Friend Item
        p.getInventory().setItem(1, new ItemBuilder(Material.SKULL_ITEM, "§6§lFreunde", 1).build());

        //Nick Item
        if(p.hasPermission("pt.nick")) {
            p.getInventory().setItem(2, new ItemBuilder(Material.NAME_TAG, "§e§lNick", 1).build());
        }

        //Silent Lobby Item
        if(p.hasPermission("pt.silentlobby")) {

            ItemBuilder builder = new ItemBuilder(Material.TNT, "§c§lSilentlobby", 1);

            if(Main.isSilentServer()){
                builder.enchant(Enchantment.DURABILITY, 10);
            }
            p.getInventory().setItem(6, builder.build());
        }

        //Spieler verstecken Item
        p.getInventory().setItem(4, new ItemBuilder(Material.BLAZE_ROD, "§5§LSpieler verstecken", 1).build());

        //Extras Item
        p.getInventory().setItem(7, new ItemBuilder(Material.CHEST, "§e§lExtras", 1).build());

        //Lobby Switcher
        p.getInventory().setItem(8, new ItemBuilder(Material.WATCH, "§f§lLobbySwitcher", 1).build());
    }

    public static void clearInv(Player p) {
        double health = 20;
        p.setMaxHealth(health);
        p.setHealth(health);

        p.setFoodLevel(25);
       // p.setAllowFlight(false);
        p.setFireTicks(0);
        p.setExp(0.0F);
        p.setLevel(0);

        for (PotionEffect effects : p.getActivePotionEffects()) {
            p.removePotionEffect(effects.getType());
        }

        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
    }
    public static void sendPlayerToServer(Player p, String servername){
        ServerInfo serverInfo = CloudAPI.getInstance().getServerInfo(servername);
        if(!(serverInfo.getPlayers().contains(p.getName()))) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF(servername);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            p.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
        } else {
            p.sendMessage(Main.getPrefix() + "Du bist bereits auf der Lobby!");
        }
    }
}
