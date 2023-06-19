package com.kryeit.commands;

import com.kryeit.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MandarCoordenadas implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player cordSender)) {
            Bukkit.getConsoleSender().sendMessage("You can't execute this command from console.");
            return false;
        }

        int x = cordSender.getLocation().getBlockX();
        int y = cordSender.getLocation().getBlockY();
        int z = cordSender.getLocation().getBlockZ();

        if (args.length != 1) {
            cordSender.sendMessage("Tienes que poner el nombre de un jugador");
            return false;
        }

        Player cordReciever = Bukkit.getPlayer(args[0]);

        if (cordReciever == null) {
            cordSender.sendMessage("Jugador no encontrado");
            return false;
        }

        BaseComponent[] components = new ComponentBuilder("")
                .append("El jugador ")
                .color(ChatColor.WHITE)
                .append(new TextComponent(cordSender.getName()))
                .color(ChatColor.GOLD)
                .append(" te ha mandado sus coordenadas: ")
                .color(ChatColor.WHITE)
                .append(new TextComponent("(" + x + ", " + y + ", " + z + ")"))
                .color(ChatColor.GOLD)
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Haz clic para ver en el BlueMap")
                        .color(ChatColor.LIGHT_PURPLE)
                        .create()))
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://map.kryeit.com/#world:" + x
                        + ":" + y + ":" + z + ":198:0:0:0:0:perspective"))
                .create();

        cordReciever.spigot().sendMessage(components);
        cordSender.sendMessage(Utils.color("&fEl jugador &6" + cordReciever.getName() + "&f ha recivido tus coordenadas."));
        return true;
    }
}

