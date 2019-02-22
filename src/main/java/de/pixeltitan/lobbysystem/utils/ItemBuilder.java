/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


/*

ItemBuilder item = new ItemBuilder(Material.DIRT, "§5POWER DIRT", 1);  //Create Item
item.setLore(lore);                                                     //Set Lore
item.enchant(Enchantment.DAMAGE_ALL, 10);                               //Enchant Sharpness 10
player.getInventory().addItem(item.build());


or with 1 Line of code

player.getInventory().addItem(new ItemBuilder(Material.DIRT, "§5POWER DIRT", 1));

 */

public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    public ItemBuilder(Material material, String itemname, int amount, byte shortcode){
        itemStack = new ItemStack(material, amount, shortcode);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemname);
    }

    public ItemBuilder(Material material, String itemname, int amount){
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemname);
    }

    public void setLore(List<String> list){
        itemMeta.setLore(list);   
    }

    public void addItemFlag(ItemFlag itemFlag) { itemMeta.addItemFlags(itemFlag); }

    public void enchant(Enchantment enchantment, int lvl){
        itemMeta.addEnchant(enchantment, lvl, true);
    }
    
    public ItemStack build(){
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
