package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = player.getLocation();
        player.sendMessage(Utils.color("&7Has muerto en: (" +
                location.getBlockX() + ", " +
                location.getBlockY() + ", " +
                location.getBlockZ() + ")"));
    }
}
