package com.stuudent.AFK.commands;

import com.stuudent.AFK.AFKAPI;
import com.stuudent.AFK.AFKCore;
import com.stuudent.AFK.data.AFKPlayer;
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
        AFKPlayer afkPlayer = AFKAPI.getPlayer(player);
        String userMessage = AFKCore.cf.getString("UserMessage", null);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', userMessage.replace("[POINT]", String.valueOf(afkPlayer.getPoint()))));
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return null;
    }
}
