package com.kryeit.commands;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TiempoJugado implements CommandExecutor {
    
    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if (args.length != 1) {
            p.sendMessage("Tienes que poner el nombre de un jugador");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            p.sendMessage("Jugador no encontrado");
            return false;
        }

        String time = Utils.getTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20);

        p.sendMessage(Utils.color("&fEl jugador &6" + player.getName() + "&f ha jugado durante " + time));

        return true;

    }
}
