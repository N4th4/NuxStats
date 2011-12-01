package net.n4th4.bukkit.nuxstats;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONArray;

public class NSPlayerListener extends PlayerListener {
    private final NuxStats plugin;

    public NSPlayerListener(NuxStats instance) {
        plugin = instance;
    }

    @SuppressWarnings("unchecked")
    public void onPlayerJoin(PlayerJoinEvent event) {
        JSONArray json = new JSONArray();
        List<Player> list = Arrays.asList(plugin.getServer().getOnlinePlayers());
        for (int i = 0; i < list.size(); i++) {
            json.add(list.get(i).getName());
        }
        plugin.write("playersName.txt", json.toJSONString());
        plugin.write("playersNumber.txt", Integer.toString(json.size()));
    }

    @SuppressWarnings("unchecked")
    public void onPlayerQuit(PlayerQuitEvent event) {
        JSONArray json = new JSONArray();
        List<Player> list = Arrays.asList(plugin.getServer().getOnlinePlayers());
        for (int i = 0; i < list.size(); i++) {
            if (!event.getPlayer().getName().equals(list.get(i).getName()))
                json.add(list.get(i).getName());
        }
        plugin.write("playersName.txt", json.toJSONString());
        plugin.write("playersNumber.txt", Integer.toString(json.size()));
    }
}
