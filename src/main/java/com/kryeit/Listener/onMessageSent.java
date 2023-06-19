package com.kryeit.Listener;

import com.kryeit.LambdaStuff;
import com.kryeit.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.kryeit.Utils.isPlayerInGroup;

public class onMessageSent implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();

        if (message.contains("atrapado") || message.contains("salir")) {
            if (!LambdaStuff.getInstance().sentTrapped.contains(p.getUniqueId())){
                p.sendMessage(Utils.color("&bSi estás atrapado en algún sitio, usa /trapped"));
                LambdaStuff.getInstance().sentTrapped.add(p.getUniqueId());
            }
        }

        if(!p.hasPermission("stuff.muted") || p.isOp()) {
            if (message.length() > 7 && message.equals(message.toUpperCase())) {
                message = message.toLowerCase();
                }
            e.setFormat(Utils.color(getColouredName(p) + "&f: ") + message);
        } else {
            e.setCancelled(true);
            p.sendMessage("Estás baneado del chat general.");
        }

    }

    public static String getColouredName(Player p) {

        String name = p.getName();
        String color;
        String group;

        if (isPlayerInGroup(p,"staff")) {
            color = "&a";
            group = "Staff";
        }
        else if (isPlayerInGroup(p,"helper")) {
            color = "&b";
            group = "Helper";
        }
        else if (isPlayerInGroup(p,"booster")) {
            color = "&5";
            group = "Booster";
        }
        else {
            color = "&7";
            group = "Default";
        }

        return color + name;
    }
}
