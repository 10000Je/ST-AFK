package com.stuudent.afk.commands;

import com.stuudent.afk.AFKAPI;
import com.stuudent.afk.AFKCore;
import com.stuudent.afk.data.PlayerData;
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
        PlayerData playerData = AFKAPI.getPlayer(player);
        String userMessage = AFKCore.cf.getString("UserMessage", null);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', userMessage.replace("[POINT]", String.valueOf(playerData.getPoint()))));
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return null;
    }
}
