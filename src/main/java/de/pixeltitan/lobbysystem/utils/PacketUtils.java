/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketUtils {
    public static void sendPacket(Packet<?> packet){
        for (Player all : Bukkit.getOnlinePlayers()){
            ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
        }
    }
    public static void sendPacket(Packet<?> packet, Player p){
        for (Player all : Bukkit.getOnlinePlayers()){
            if(!(all.equals(p))){
                ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
    public static void sendPacket(Player p, Packet<?> packet){
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }
}
