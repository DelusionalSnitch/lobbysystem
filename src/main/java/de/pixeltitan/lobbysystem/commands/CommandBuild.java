/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.commands;

import de.pixeltitan.lobbysystem.main.Main;
import de.pixeltitan.lobbysystem.utils.LobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandBuild implements CommandExecutor {
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cs instanceof Player) {
            Player p = (Player) cs;
            if(p.hasPermission("pt.build")) {
                if(args.length == 0) {
                    if(Main.getListManager().getBuildPlayers().contains(p)){
                        Main.getListManager().getBuildPlayers().remove(p);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                        p.sendMessage(Main.getPrefix() + "§cDu hast den Baumodus verlassen");
                        LobbyUtils.clearInv(p);
                        LobbyUtils.giveLobbyItems(p);
                        p.setGameMode(GameMode.SURVIVAL);


                    }else{
                        if(Main.getListManager().getBuildPlayers().add(p)){
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            p.setGameMode(GameMode.CREATIVE);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                            p.sendMessage(Main.getPrefix()+ "§aDu bist jetzt im Baumodus");
                            LobbyUtils.clearInv(p);
                        }


                    }

                } else if(args.length == 1) {
                    Player spieler = Bukkit.getPlayer(args[0]);
                    if (spieler != null) {
                        if(Main.getListManager().getBuildPlayers().contains(spieler)){
                            Main.getListManager().getBuildPlayers().remove(spieler);
                            spieler.setGameMode(GameMode.SURVIVAL);
                            spieler.playSound(spieler.getLocation(), Sound.ANVIL_LAND, 1, 1);
                            p.sendMessage(Main.getPrefix()+ "Du hast die Baurechte von " + spieler.getName() + " §centzogen");
                            spieler.sendMessage(Main.getPrefix()+"§cDeine Baurechte wurden dir entzogen");
                            LobbyUtils.clearInv(p);
                            LobbyUtils.giveLobbyItems(p);

                        }else{
                            if(Main.getListManager().getBuildPlayers().add(spieler)){
                                spieler.setGameMode(GameMode.CREATIVE);
                                spieler.playSound(spieler.getLocation(), Sound.LEVEL_UP, 1, 1);
                                p.sendMessage(Main.getPrefix()+ "Du hast die Baurechte von " + spieler.getName() + " §aaktiviert");
                                spieler.sendMessage(Main.getPrefix()+ "§aDeine Baurechte wurden aktiviert");
                            }
                        }
                    } else {
                        p.sendMessage(Main.getPrefix() + "§cDer Spieler ist nicht online");
                    }
                } else {
                    p.sendMessage(Main.getPrefix() + "§cSyntax: /Build <Spieler>");
                }

            } else {
                p.sendMessage(Main.getPrefix() + "§cDu hast keine Berechtigung dazu.");
            }

        }
        return true;
    }

}

