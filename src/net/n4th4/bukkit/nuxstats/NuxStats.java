package net.n4th4.bukkit.nuxstats;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;

public class NuxStats extends JavaPlugin {
    private final NSPlayerListener playerListener = new NSPlayerListener(this);
    public Logger                  log;
    public int                     playersCount   = 0;

    public NuxStats() {
        try {
            new File("plugins/NuxStats/").mkdirs();
            new File("plugins/NuxStats/playersNumber.txt").createNewFile();
            new File("plugins/NuxStats/playersName.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void onEnable() {
        log = this.getServer().getLogger();

        JSONArray json = new JSONArray();
        List<Player> list = Arrays.asList(getServer().getOnlinePlayers());
        for (int i = 0; i < list.size(); i++) {
            json.add(list.get(i).getName());
        }
        write("playersName.txt", json.toJSONString());
        write("playersNumber.txt", Integer.toString(json.size()));

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
    }

    public void onDisable() {
        write("playersNumber.txt", "[]");
        write("playersNumber.txt", Integer.toString(0));
    }
    
    public void write(String file, String text) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("plugins/NuxStats/" + file)), "8859_1");
            osw.write(text);
            osw.close();
        } catch (FileNotFoundException e) {
            log.severe("[NuxStats] File not found : plugins/NuxStats/" + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
