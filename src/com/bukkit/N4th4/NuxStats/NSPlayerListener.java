package com.bukkit.N4th4.NuxStats;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class NSPlayerListener extends PlayerListener {
    private final NuxStats plugin;

    public NSPlayerListener(NuxStats instance) {
        plugin = instance;
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.playersCount++;
        plugin.writePlayersNumber();
    }

    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.playersCount--;
        plugin.writePlayersNumber();
    }
}
