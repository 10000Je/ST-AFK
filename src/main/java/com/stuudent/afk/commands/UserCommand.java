package com.stuudent.afk.commands;

import com.stuudent.afk.AfkAPI;
import com.stuudent.afk.AfkCore;
import com.stuudent.afk.interfaces.AfkPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class UserCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equals("잠수"))
            return false;
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        AfkPlayer afkPlayer = AfkAPI.getPlayer(player);
        String userMessage = AfkCore.cf.getString("UserMessage", null);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', userMessage.replace("%afk_point%", String.valueOf(afkPlayer.getPoint()))));
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return null;
    }
}
