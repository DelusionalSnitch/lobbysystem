/*
 *     Copyright (c) 2018 Slowloris.de                                                         
 *     Development: Weichtier & Julian
 *                                                                                                                                 
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!             
 */

package de.pixeltitan.lobbysystem.utils.nick;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;

public class Skin {
    String uuid;
    String name;
    String value;
    String signatur;

    public Skin(String uuid) {
        this.uuid = uuid;
        this.load();
    }

    private void load() {
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid + "?unsigned=false");
            URLConnection uc = url.openConnection();
            uc.setUseCaches(false);
            uc.setDefaultUseCaches(false);
            uc.addRequestProperty("User-Agent", "Mozilla/5.0");
            uc.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
            uc.addRequestProperty("Pragma", "no-cache");
            String json = (new Scanner(uc.getInputStream(), "UTF-8")).useDelimiter("\\A").next();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            JSONArray properties = (JSONArray)((JSONObject)obj).get("properties");

            for(int i = 0; i < properties.size(); ++i) {
                try {
                    JSONObject property = (JSONObject)properties.get(i);
                    String name = (String)property.get("name");
                    String value = (String)property.get("value");
                    String signature = property.containsKey("signature") ? (String)property.get("signature") : null;
                    this.name = name;
                    this.value = value;
                    this.signatur = signature;
                } catch (Exception exc) {
                    Bukkit.getLogger().log(Level.WARNING, "Failed to apply auth property", exc);
                }
            }
        } catch (Exception var13) {
            ;
        }

    }

    public String getSkinValue() {
        return this.value;
    }

    public String getSkinName() {
        return this.name;
    }

    public String getSkinSignatur() {
        return this.signatur;
    }
}
