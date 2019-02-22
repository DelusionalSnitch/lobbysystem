/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian                                                                                
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.data;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import de.dytanic.cloudnet.lib.player.permission.GroupEntityData;
import de.dytanic.cloudnet.lib.player.permission.PermissionPool;
import de.pixeltitan.lobbysystem.utils.nick.NickUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.Iterator;

public class Data {

    public static String getRang(Player player) {
        OfflinePlayer offlinePlayer = CloudAPI.getInstance().getOfflinePlayer(player.getUniqueId());
        Iterator localIterator = offlinePlayer.getPermissionEntity().getGroups().iterator();
        if (localIterator.hasNext()) {
            GroupEntityData groupEntityData = (GroupEntityData) localIterator.next();
            return groupEntityData.getGroup();
        } else {
            return null;
        }
    }

    public static String getChatPrefix(Player player, String message) {
        if(NickUtils.isNicked(player)) {
            return "§7Spieler §8| §7" + player.getName() + " §8» §7" + message;
        } else {
            if (getRang(player).equalsIgnoreCase("Owner")) {
                return Data.getRangColor(player) + Data.getRang(player) + " §8| " + Data.getRangColor(player) + player.getName() + " §8» §7" + message;
            } else if (getRang(player).equalsIgnoreCase("Admin")) {
                return Data.getRangColor(player) + Data.getRang(player) + " §8| " + Data.getRangColor(player) + player.getName() + " §8» §7" + message;
            } else if (getRang(player).equalsIgnoreCase("YouTuber")) {
                return Data.getRangColor(player) + Data.getRang(player) + " §8| " + Data.getRangColor(player) + player.getName() + " §8» §7" + message;
            } else {
                return Data.getRangColor(player) + Data.getRang(player) + " §8| " + Data.getRangColor(player) + player.getName() + " §8» §7" + message;
            }
        }
    }

    public static String getRangColor(Player player) {
        if(getRang(player).equalsIgnoreCase("Owner")) {
            return "§4";
        } else if(getRang(player).equalsIgnoreCase("Admin")) {
            return "§c";
        } else if(getRang(player).equalsIgnoreCase("YouTuber")) {
            return "§5";
        } else {
            return "§7";
        }
    }

}
