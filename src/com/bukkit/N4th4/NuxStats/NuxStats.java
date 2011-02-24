package com.bukkit.N4th4.NuxStats;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NuxStats extends JavaPlugin {
    private final NSPlayerListener playerListener;
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public int playersCount;

    public NuxStats() {
        NSLogger.initialize();
        playerListener = new NSPlayerListener(this);
        playersCount = 0;
    }

    public void onEnable() {
        playersCount = getServer().getOnlinePlayers().length;
        writePlayersNumber();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        NSLogger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " est activé !");
    }

    public void onDisable() {
        playersCount = 0;
        writePlayersNumber();
    }

    public void writePlayersNumber() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("plugins/NuxStats/playersNumber.txt")), "8859_1");
            osw.write(String.valueOf(playersCount));
            osw.close();
        } catch (FileNotFoundException e) {
            NSLogger.severe("Fichier non trouvé : plugins/NuxStats/playersNumber.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
