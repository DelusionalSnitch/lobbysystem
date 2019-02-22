/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleBuilder {


    private String title = null;
    private String subtitle = null;
    private int length = 0;
    private int fadein = 0;
    private int fadeout = 0;

    public TitleBuilder(String title, String subtitle, int fadeout, int lengthint, int fadein){
        this.title = title;
        this.subtitle = subtitle;
        this.length = length;
        this.fadein = fadein;
        this.fadeout = fadeout;
    }

    public void send(Player p){
        IChatBaseComponent titleText = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");
        IChatBaseComponent subtitleText = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\",color:" + ChatColor.GREEN.name().toLowerCase() + "}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleText);
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleText);
        PacketPlayOutTitle length = new PacketPlayOutTitle(15, 60, 15);

        PacketUtils.sendPacket(p, title);
        PacketUtils.sendPacket(p, subtitle);
        PacketUtils.sendPacket(p, length);
    }

}
