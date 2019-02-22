/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class HideGui {
    public static String allonName = "§a§lAlle anzeigen";
    public static String allOffName = "§c§lAlle verstecken";
    public static String onlyVipName = "§5§lNur VIPs Anzeigen";
    private static Inventory hideinv = Bukkit.createInventory(null, 1*9, "§5§lSpieler Verstecken");

    public static Inventory getHideinv(){
        return hideinv;
    }

    public static void setHideinv(Inventory hideinv) {
        HideGui.hideinv = hideinv;
    }
}
