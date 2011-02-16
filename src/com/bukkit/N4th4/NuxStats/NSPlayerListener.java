package com.bukkit.N4th4.NuxStats;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class NSPlayerListener extends PlayerListener {
    private final NuxStats plugin;

    public NSPlayerListener(NuxStats instance) {
        plugin = instance;
    }
    public void onPlayerJoin(PlayerEvent event) {
    	NSLogger.severe("Test");
    	plugin.playersCount++;
    	plugin.writePlayersNumber();
    }
    public void onPlayerQuit(PlayerEvent event) {
    	plugin.playersCount--;
    	plugin.writePlayersNumber();
    }
}

