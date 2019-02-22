/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.main;

import de.dytanic.cloudnet.api.CloudAPI;
import de.pixeltitan.lobbysystem.commands.CommandBuild;
import de.pixeltitan.lobbysystem.commands.CommandSpawn;
import de.pixeltitan.lobbysystem.commands.SpazzCommand;
import de.pixeltitan.lobbysystem.guis.HideGui;
import de.pixeltitan.lobbysystem.guis.NavigatorGui;
import de.pixeltitan.lobbysystem.listeners.*;
import de.pixeltitan.lobbysystem.manager.ListManager;
import de.pixeltitan.lobbysystem.utils.ItemBuilder;
import de.pixeltitan.lobbysystem.utils.nick.NickUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {


    //TODO: Die 4 Variablen vllt. per Config zugänglich machen damit sie einfacher geändert werden können
    private static String prefix = "§6>> §eLobby §6|§7 ";
    private static String silentserver = "Silentlobby-1";
    private static String lobbyserver = "Test-1";
    private static String lobbygroup = "Test";
    private static ListManager listManager;
    private static Main instance;
    private PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        instance = this;

        if(!new File(getDataFolder() + File.separator, "config.yml").exists()){
            getConfig().options().copyDefaults(true);
            saveConfig();
        }

        pm.registerEvents(new AsyncChatEvent(), this);
        pm.registerEvents(new BlockedEvents(), this);
        pm.registerEvents(new InteractEvent(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new CommandEvent(), this);
        pm.registerEvents(new Bausystem(), this);
        pm.registerEvents(new MoveEvent(), this);
        pm.registerEvents(new FlightListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");

        getCommand("build").setExecutor(new CommandBuild());
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("spazz").setExecutor(new SpazzCommand());
        initializeAll();


    }



    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
    public static String getPrefix() {
        return prefix;
    }
    public static ListManager getListManager() {
        return listManager;
    }
    public static String getSilentserver() {
        return silentserver;
    }
    public static String getLobbyserver() {
        return lobbyserver;
    }

    public static String getLobbygroup() {
        return lobbygroup;
    }
    public static CloudAPI getCloudAPI(){
        return CloudAPI.getInstance();
    }

    public static boolean isSilentServer(){
        return getInstance().getConfig().getBoolean("Config.SilentServer");
    }
    private void initializeAll(){
        instance = this;
        listManager = new ListManager();

        NickUtils.setNickList(getConfig().getStringList("Config.Nicknames"));

        Inventory hideinv = HideGui.getHideinv();
        hideinv.setItem(2, new ItemBuilder(Material.getMaterial(351), HideGui.allonName, 1, (byte) 10).build());
        hideinv.setItem(4, new ItemBuilder(Material.getMaterial(351), HideGui.allOffName, 1, (byte) 1).build());
        hideinv.setItem(6, new ItemBuilder(Material.getMaterial(351), HideGui.onlyVipName, 1, (byte) 5).build());
        HideGui.setHideinv(hideinv);

        Inventory navinv = NavigatorGui.getNavinv();
        navinv.setItem(3, new ItemBuilder(Material.IRON_SWORD, "§b§LSURVIVAL GAMES", 1).build());
        navinv.setItem(6, new ItemBuilder(Material.SUGAR, "§b§lPIXEL SHOOTER", 1).build());
        navinv.setItem(18, new ItemBuilder(Material.PAPER, "§b§lFLASH", 1).build());
        navinv.setItem(26, new ItemBuilder(Material.LEATHER_BOOTS, "§b§lUnbekannt", 1).build());
        navinv.setItem(31, new ItemBuilder(Material.SLIME_BALL, "§b§lSPAWN", 1).build());
        navinv.setItem(36, new ItemBuilder(Material.ITEM_FRAME, "§b§lMONTAGS MALER", 1).build());
        navinv.setItem(44, new ItemBuilder(Material.BED, "§b§lBEDWWARS", 1).build());

        NavigatorGui.setNavinv(navinv);

    }
}