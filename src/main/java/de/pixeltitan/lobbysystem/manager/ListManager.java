/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.manager;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListManager {
    private List<Player> buildPlayers;
    private List<Player> flightPlayers;
    private HashMap<Player, Integer> playerhider = new HashMap<Player, Integer>(); //0 = hide all, 1 = show VIP, 2 =how all



    public ListManager(){
        this.buildPlayers = new ArrayList<Player>();
        this.flightPlayers = new ArrayList<Player>();
    }

    public List <Player> getBuildPlayers() {
        return buildPlayers;
    }
    public List <Player> getFlightPlayers() {
        return flightPlayers;
    }
}
