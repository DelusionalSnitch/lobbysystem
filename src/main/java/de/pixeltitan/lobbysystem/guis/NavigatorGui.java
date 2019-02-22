/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NavigatorGui {
    private static Inventory navinv = Bukkit.createInventory(null, 5*9, "§e§l>> §r§7Teleporter");

    public static Inventory getNavinv(){
        return navinv;
    }

    public static void setNavinv(Inventory navinv) {
        NavigatorGui.navinv = navinv;
    }
    public static Inventory getNavSetInv(){
        Inventory inv = Bukkit.createInventory(null, 5*9, "§e§l>> §r§7Teleporter-Setzen");
        ItemStack[] navinvcontent = navinv.getContents();
        inv.setContents(navinvcontent);
        return inv;
    }
}
