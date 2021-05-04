package com.github.huda0209.endermanescapecanceller;

import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class configLoad {

    public static Boolean EnderManEscapeCancellMode = false;
    public static List<String> WorldName =  new ArrayList<String>();
    private static Configuration config;

    public static void loadConfig(EndermanEscapeCanceller pl){
        try{
            config = pl.getConfig();
        }catch (Exception exception){
            System.out.println("While load the config file, occurred error.");
            return;
        }
        WorldName = new ArrayList<String>();;
        EnderManEscapeCancellMode = config.getBoolean("EnderManEscapeCancellMode");

        if(!config.contains("WorldName")) return;
        for (String key : config.getConfigurationSection("WorldName").getKeys(false)){
            WorldName.add(config.getString("WorldName." + key).toLowerCase(Locale.ROOT));
        }
    }

    public static void setEnderManEscapeCancellMode(Boolean bool,EndermanEscapeCanceller pl){
        EnderManEscapeCancellMode = bool;
        config.set("EnderManEscapeCancellMode",bool);
        pl.saveConfig();
    }

    public static boolean addWorldName(String worldName,EndermanEscapeCanceller pl){
        if(WorldName.indexOf(worldName)>0) return false;

        WorldName.add(worldName);
        config.set("WorldName",WorldName);
        pl.saveConfig();
        return true;
    }

    public static boolean removeWorldName(String worldName,EndermanEscapeCanceller pl){
        if(WorldName.indexOf(worldName)==-10) return false;

        WorldName.remove(worldName);
        config.set("WorldName",WorldName);
        pl.saveConfig();
        return true;
    }
}
