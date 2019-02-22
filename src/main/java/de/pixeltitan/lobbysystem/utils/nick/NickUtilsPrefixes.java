/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian                                                                                
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils.nick;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.pixeltitan.lobbysystem.main.Main;
import de.pixeltitan.lobbysystem.utils.PacketUtils;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NickUtilsPrefixes {
    private static final HashMap<Player, String> realnames = new HashMap<Player, String>();
    private static final HashMap<Player, String> oldprefixes = new HashMap<Player, String>();
    private static List<String> nicknames = null;


    public static void nick(Player p, String prefix){

        if(isNicked(p)){
            unnick(p);
            return;
        }

        String nickname = pickRandomNickname();
        if(nickname == null){
            p.sendMessage("§cKeine Nicknamen verfügbar! Bitte kontaktiere einen Administrator!");
        }
        realnames.put(p, p.getName());
        oldprefixes.put(p, p.getDisplayName().replace(p.getName(), ""));
        nicknames.remove(nickname);
        changeProperties(nickname, p, prefix);
    }
    public static void nick(Player p){
        nick(p, "");
    }


    public static void unnick(Player p){
        nicknames.add(p.getName());
        changeProperties(realName(p), p, oldprefixes.get(p));
        realnames.remove(p);
    }


    public static void changeProperties(final String name, final Player player, final String prefix) {
        try {

            final CraftPlayer cp = (CraftPlayer) player;

            Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[]) null);
            Object entityPlayer = getHandle.invoke(player);
            Class<?> entityHuman = entityPlayer.getClass().getSuperclass();
            Field bH = entityHuman.getDeclaredField("bH");
            bH.setAccessible(true);
            bH.set(entityPlayer, new GameProfile(player.getUniqueId(), name));

            player.setPlayerListName(prefix + player.getName());
            Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
            board.registerNewTeam(player.getName());
            board.getTeam(player.getName()).setPrefix(prefix);
            board.getTeam(player.getName()).addPlayer(player);
            for (Player all : Bukkit.getOnlinePlayers()){
                all.setScoreboard(board);
            }


            try {
                GameProfile gp = cp.getProfile();
                gp.getProperties().clear();
                if (getUUID(name) != null) {
                    Skin skin = new Skin(getUUID(name));
                    if (skin.getSkinName() != null) {
                        gp.getProperties().put(skin.getSkinName(), new Property(skin.getSkinName(), skin.getSkinValue(), skin.getSkinSignatur()));
                    }
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
            PacketUtils.sendPacket(destroy, player);
            PacketPlayOutPlayerInfo removeTab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
            PacketUtils.sendPacket(removeTab);


            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                public void run() {
                    PacketPlayOutPlayerInfo addTab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
                    PacketUtils.sendPacket(addTab);

                    PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                    PacketUtils.sendPacket(spawn, player);
                }
            }, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setNickList(List<String> list){
        nicknames = list;
    }

    public static List<String> getNickList(){
        return nicknames;
    }

    private static String getUUID(String name) {
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replaceAll("-", "");
    }

    public static String pickRandomNickname(){
        if(nicknames.size() > 0){
            return nicknames.get(new Random().nextInt(nicknames.size()));
        }else {
            return null;
        }
    }
    public static String realName(Player p){
        return realnames.get(p);
    }

    public static boolean isNicked(Player p){
        return realnames.containsKey(p);
    }
}