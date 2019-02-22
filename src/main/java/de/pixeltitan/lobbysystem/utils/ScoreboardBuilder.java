/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ScoreboardBuilder {
    private Scoreboard board = new Scoreboard();
    private ScoreboardObjective obj = board.registerObjective("board", IScoreboardCriteria.b);
    private ArrayList<ScoreboardScore> scores = new ArrayList<ScoreboardScore>();
    public ScoreboardBuilder(String title){

        obj.setDisplayName(title);


    }

    public void setLine(String content, int line){
        ScoreboardScore s = new ScoreboardScore(board, obj, content);
        s.setScore(line);
        scores.add(s);
    }

    public void sendScoreboard(Player p){
        PacketPlayOutScoreboardObjective create = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardObjective destroy = new PacketPlayOutScoreboardObjective(obj, 1);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

        PacketUtils.sendPacket(destroy, p);
        PacketUtils.sendPacket(create, p);
        PacketUtils.sendPacket(display, p);

        for (ScoreboardScore score : scores){
            PacketPlayOutScoreboardScore scorepacket = new PacketPlayOutScoreboardScore(score);
            PacketUtils.sendPacket(p, scorepacket);
        }
    }
    public void sendScoreboard(){
        for (Player all : Bukkit.getOnlinePlayers()){
            sendScoreboard(all);
        }
    }
}
