package com.stuudent.afk.commands;

import com.stuudent.afk.AFKAPI;
import com.stuudent.afk.AFKCore;
import com.stuudent.afk.data.AllData;
import com.stuudent.afk.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!cmd.getName().equals("잠수관리"))
            return false;
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        String errorMessage = ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("CommandError", ""));
        if(args.length == 0) {
            for(String text : AFKCore.cf.getStringList("AdminMessage")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
            }
            return false;
        }
        if(args[0].equals("지급") || args[0].equals("차감") || args[0].equals("설정")) {
            if(args.length <= 2) {
                player.sendMessage(errorMessage);
                return false;
            }
            //noinspection deprecation
            if(!Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore()) {
                player.sendMessage(errorMessage);
                return false;
            }
            try {
                //noinspection deprecation
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
                PlayerData playerData = AFKAPI.getPlayer(targetPlayer);
                AllData allData = AFKAPI.getData();
                int point = Integer.parseInt(args[2]);
                if(point < 0) {
                    player.sendMessage(errorMessage);
                    return false;
                }
                if(args[0].equals("지급")) {
                    playerData.addPoint(point);
                    allData.save();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("AddMessage.ForAdmin", "")
                            .replace("[PLAYER]", targetPlayer.getName()).replace("[POINT]", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = targetPlayer.getPlayer();
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("AddMessage.ForUser", "")
                                .replace("[POINT]", String.valueOf(point))));
                    }
                    return false;
                }
                if(args[0].equals("차감")) {
                    playerData.removePoint(point);
                    allData.save();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("RemoveMessage.ForAdmin", "")
                            .replace("[PLAYER]", targetPlayer.getName()).replace("[POINT]", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = (Player) targetPlayer;
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("RemoveMessage.ForUser", "")
                                .replace("[POINT]", String.valueOf(point))));
                    }
                    return false;
                }
                if(args[0].equals("설정")) {
                    playerData.setPoint(point);
                    allData.save();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("SetMessage.ForAdmin", "")
                            .replace("[PLAYER]", targetPlayer.getName()).replace("[POINT]", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = (Player) targetPlayer;
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("SetMessage.ForUser", "")
                                .replace("[POINT]", String.valueOf(point))));
                    }
                    return false;
                }
                return false;
            } catch(NumberFormatException e) {
                player.sendMessage(errorMessage);
                return false;
            }
        }
        if(args[0].equals("확인")) {
            if(args.length <= 1) {
                player.sendMessage(errorMessage);
                return false;
            }
            //noinspection deprecation
            if(!Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore()) {
                player.sendMessage(errorMessage);
                return false;
            }
            //noinspection deprecation
            OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
            PlayerData playerData = AFKAPI.getPlayer(targetPlayer);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("SearchMessage.ForAdmin", "")
                    .replace("[PLAYER]", targetPlayer.getName()).replace("[POINT]", String.valueOf(playerData.getPoint()))));
            return false;
        }
        player.sendMessage(errorMessage);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!cmd.getName().equals("잠수관리"))
            return null;
        if(!(sender instanceof Player))
            return null;
        List<String> subCommands = Arrays.asList("지급", "차감", "설정", "확인");
        if(args.length == 1) {
            List<String> available = new ArrayList<>();
            for(String text : subCommands) {
                if(text.toLowerCase().startsWith(args[0].toLowerCase()))
                    available.add(text);
            }
            return available;
        }
        if(subCommands.contains(args[0]) && args.length == 2) {
            List<String> offlineUsers = new ArrayList<>();
            for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                if(player.getName().toLowerCase().startsWith(args[1].toLowerCase()))
                    offlineUsers.add(player.getName());
            }
            return offlineUsers;
        }
        return null;
    }

}
