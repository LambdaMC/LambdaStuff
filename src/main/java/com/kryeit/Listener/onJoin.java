package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onNewPlayerJoin (PlayerJoinEvent e){

        Player p = e.getPlayer();

        if (p.hasPlayedBefore()) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(p.getName())) {
                p.sendMessage(Utils.color("&bBienvenido a LambdaCraft! mira las /reglas y /posthelp para información adicional."));
                p.sendMessage(Utils.color("&bPara la guía de protección: https://docs.griefdefender.com/ y para ver la GUI general /gd."));
                p.sendMessage(Utils.color("&aPor favor, recuerda que NO tenemos nada que ver con el servidor de LandaCraft ni con sus administradores"));
                continue;
            }
            player.sendMessage(Utils.color("&b" + p.getName()
                    + " se ha unido a LambdaCraft por primera vez! Bienvenido!"));
        }
    }
}
