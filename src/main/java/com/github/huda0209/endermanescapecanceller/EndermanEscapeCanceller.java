package com.github.huda0209.endermanescapecanceller;

import com.github.huda0209.endermanescapecanceller.command.CommandHandler;
import com.github.huda0209.endermanescapecanceller.listener.EndermanEscapeEvent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndermanEscapeCanceller extends JavaPlugin implements CommandExecutor {

    final String pluginName = this.getDescription().getName();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        getCommand("emec").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new EndermanEscapeEvent(this),this);
        configLoad.loadConfig(this);

        String[] EnableMessage = {"=============================","Plugin Name : "+pluginName ,"Author : "+ this.getDescription().getAuthors(),"============================="};
        for (String s : EnableMessage) {
            getLogger().info(s);
        }
        getLogger().info("EnderManEscapeCancellMode was "+configLoad.EnderManEscapeCancellMode.toString()+" now.");

    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName+"is disable");
    }

    public void logger(String msg){
        getLogger().info(msg);
    }
}