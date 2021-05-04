package com.github.huda0209.endermanescapecanceller.command;

import com.github.huda0209.endermanescapecanceller.EndermanEscapeCanceller;
import com.github.huda0209.endermanescapecanceller.configLoad;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandHandler implements CommandExecutor {

    private final EndermanEscapeCanceller plugin;
    public CommandHandler(EndermanEscapeCanceller pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==0) return false;
        switch (args[0].toLowerCase(Locale.ROOT)){
            case "setmode" :
                if(!sender.hasPermission("EndermanEscapeCanceller.SetMode")){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c You don't have permission!");
                    return true;
                }
                if(args.length!=2){
                    return false;
                }
                if(!args[1].equalsIgnoreCase("true") && !args[1].equalsIgnoreCase("false")){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c Please specify SetMode with true or false.");
                    return true;
                }

                Boolean mode = args[1].equalsIgnoreCase("true");

                sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§a setEnderManEscapeCancellMode set to "+ mode.toString() +".");
                configLoad.setEnderManEscapeCancellMode(mode,plugin);
                break;

            case "addworld":
                if(!sender.hasPermission("EndermanEscapeCanceller.World")){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c You don't have permission!");
                    return true;
                }
                List<World> worlds = sender.getServer().getWorlds();
                List<String> worldNames = new ArrayList<String>();
                String AddworldName = args[1].toLowerCase(Locale.ROOT);

                for(World key : worlds){
                    worldNames.add(key.getName());
                }

                if(worldNames.indexOf(AddworldName)==-1){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c This world("+AddworldName+") is NOT found on this server.");
                    return true;
                }

                configLoad.addWorldName(AddworldName,plugin);
                sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§a Add World :"+AddworldName);
                break;


            case "delworld":
                if(!sender.hasPermission("EndermanEscapeCanceller.World")){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c You don't have permission!");
                    return true;
                }
                String DelWorldName = args[1].toLowerCase(Locale.ROOT);

                if(configLoad.WorldName.indexOf(DelWorldName)==-1){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c This world("+DelWorldName+") is NOT found in serverList.");
                    return true;
                }

                configLoad.removeWorldName(DelWorldName,plugin);
                sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§a remove World :"+DelWorldName);
                break;


            case "reload":
                if(!sender.hasPermission("EndermanEscapeCanceller.Reload")){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c You don't have permission!");
                    return true;
                }
                try {
                    configLoad.loadConfig(plugin);
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§a Reload the config file.");
                }catch(Exception e){
                    sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c While reload the config file, occurred error. Please check console.");
                    System.out.println(e.toString());
                }
                break;

            default:
                sender.sendMessage("§9[" + plugin.getDescription().getName() + "]§c Unknown command.");
                break;
        }
        return true;
    }
}
