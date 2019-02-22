/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.listeners;

import de.pixeltitan.lobbysystem.utils.LobbyUtils;
import de.pixeltitan.lobbysystem.utils.ScoreboardBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    //Info: Alle Lobby Items abgeguckt von Bergwerklabs Stand: 24.04.2018
    //Falls irgendwas unklar sein sollte, ts kommen
    @EventHandler
    public static void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        final Player p = event.getPlayer();
        String name = p.getName();

        //Items, Effekte, Rüstung clearen
        LobbyUtils.clearInv(p);
        LobbyUtils.giveLobbyItems(p);


        /*

        Willkommen,     7
        <Spieler>       6
        LEER            5
        Server:         4
        <Server>        3
                        2
        Forum:          1
        pixeltitan.de   0
         */
                ScoreboardBuilder builder = new ScoreboardBuilder("§e§lLobby");
                builder.setLine("§aWillkommen,", 7);
                builder.setLine(p.getName(), 6);
                builder.setLine("§c", 5);
                builder.setLine("§eServer:", 4);
                builder.setLine("§eLobby", 3);
                builder.setLine("§e", 2);
                builder.setLine("§9Forum:", 1);
                builder.setLine("§6www.pixeltitan.de", 0);
                builder.sendScoreboard(p);

/*
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        board.registerNewTeam("001admin");
        board.getTeam("001admin").setPrefix("§cA §7| §c ");
        board.registerNewTeam("default");
        board.getTeam("default").setPrefix("§a");

        String team;
        if(p.hasPermission("pt.admin")) {
            team = "001admin";
        }else {
            team = "default";
        }

        board.getTeam(team).addPlayer(p);
        p.setDisplayName(board.getTeam(team).getPrefix() + name);
        for (Player all : Bukkit.getOnlinePlayers()){
            all.setScoreboard(board);
        }
         */

    }
    
}
