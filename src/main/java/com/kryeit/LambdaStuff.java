package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.*;
import com.kryeit.commands.Mapa;
import com.kryeit.tab.BasicPlayerTab;
import com.kryeit.tab.PlayerTab;
import com.kryeit.tab.ReturnEmptyTab;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class LambdaStuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI = new AFKPlusPlayerAPI();
    public final List<UUID> sentTrapped = new ArrayList<>();
    public static LambdaStuff instance;
    public final List<UUID> flyEnabled = new ArrayList<>();
    public final List<String> offlinePlayers = new ArrayList<>();

    public void onEnable () {

        instance = this;

        registerEvent(new onMessageSent());
        registerEvent(new onAFKToggle());
        registerEvent(new onJoin());
        registerEvent(new onBlockPlace());
        registerEvent(new onBlockInteract());
        registerEvent(new onWeatherChange());
        registerEvent(new onDeath());

        registerBasicCommand("online", new Online());
        registerBasicCommand("discord", new Discord());
        registerBasicCommand("reglas", new Reglas());
        registerBasicCommand("mapa", new Mapa());
        registerBasicCommand("fly", new Fly());

        registerCommand("invsee", new InvSee(), new BasicPlayerTab());
        registerCommand("enderinvsee", new EnderInvSee(), new BasicPlayerTab());
        registerCommand("mandarcoordenadas", new MandarCoordenadas(), new BasicPlayerTab());
        registerCommand("tiempojugado", new TiempoJugado(), new BasicPlayerTab());

        registerCommand("ultimaconexion", new UltimaConexion(), new PlayerTab());

    }

    public void registerEvent (Listener listener) {
        getServer().getPluginManager().registerEvents(listener,this);
    }

    public void registerCommand (String command, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(tabCompleter);
    }

    public void registerBasicCommand (String command, CommandExecutor commandExecutor) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(new ReturnEmptyTab());
    }



    public void onDisable() {
    }

    public static LambdaStuff getInstance() {
        return instance;
    }
}
