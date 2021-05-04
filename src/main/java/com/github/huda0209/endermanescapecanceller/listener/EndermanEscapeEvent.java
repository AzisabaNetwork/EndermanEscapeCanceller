package com.github.huda0209.endermanescapecanceller.listener;

import com.github.huda0209.endermanescapecanceller.EndermanEscapeCanceller;
import com.github.huda0209.endermanescapecanceller.configLoad;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EndermanEscapeEvent implements Listener {

    private final EndermanEscapeCanceller plugin;
    public EndermanEscapeEvent(EndermanEscapeCanceller pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void EndermanEscape(com.destroystokyo.paper.event.entity.EndermanEscapeEvent event){

        String WorldName = event.getEntity().getWorld().getName();

        if(!configLoad.EnderManEscapeCancellMode) return;
        if(configLoad.WorldName.indexOf(WorldName)==-1) return;
        if(!(event.getEntity() instanceof Enderman)) return;

        event.setCancelled(true);

    }
}
