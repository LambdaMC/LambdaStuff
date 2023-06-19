package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onPlace (BlockPlaceEvent e){

        String b = e.getBlock().getType().toString();
        Player pl = e.getPlayer();

        if(!target().contains(b)) return;

        String material = "";

        for (String s : target()) {
            if(s.equals(b)) {
                material = s;
                break;
            }
        }

        if (material.equals("")) {
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (p.getGameMode().equals(GameMode.SPECTATOR)) continue;

            if (p.equals(pl)) continue;

            if (!p.getWorld().equals(pl.getWorld())) continue;

            if (p.getLocation().distance(e.getBlock().getLocation()) < 5 && p.getWorld().equals(pl.getWorld())) {
                pl.sendMessage(Utils.color("&cYou cant place &6minecraft:"+ material.toLowerCase()+"&c near another player"));
                e.setCancelled(true);
            }

        }
    }

    public List<String> target () {
        List<String> target = new ArrayList<>();
        target.add("FIRE");
        target.add("MAGMA_BLOCK");
        target.add("OBSIDIAN");
        return target;
    }
}
