/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian                                                                                
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.commands;

import de.pixeltitan.lobbysystem.main.Main;
import de.pixeltitan.lobbysystem.utils.LobbyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

public class SpazzCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("pt.xyz")){
                if(args.length == 1){

                    if(args[0].equalsIgnoreCase("highupinthesky")){
                        p.setVelocity(new Vector(0, 50, 0));
                    }else if(args[0].equalsIgnoreCase("bumbum")){
                        TNTPrimed tnt = (TNTPrimed) p.getLocation().getWorld().spawn(p.getLocation(), TNTPrimed.class);
                        tnt.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
                    }else if(args[0].equalsIgnoreCase("servers")){

                    }

                }
            }
        }

        return false;
    }
}
