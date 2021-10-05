package com.stuudent.afk.commands;

import com.stuudent.afk.AfkAPI;
import com.stuudent.afk.AfkCore;
import com.stuudent.afk.data.PlayerData;
import com.stuudent.afk.interfaces.AfkPlayer;
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
        String errorMessage = ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("CommandError", ""));
        if(args.length == 0) {
            for(String text : AfkCore.cf.getStringList("AdminMessage")) {
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
                AfkPlayer afkPlayer = AfkAPI.getPlayer(targetPlayer);
                int point = Integer.parseInt(args[2]);
                if(point < 0) {
                    player.sendMessage(errorMessage);
                    return false;
                }
                if(args[0].equals("지급")) {
                    afkPlayer.addPoint(point);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("AddMessage.ForAdmin", "")
                            .replace("%afk_player%", targetPlayer.getName()).replace("%afk_point%", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = targetPlayer.getPlayer();
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("AddMessage.ForUser", "")
                                .replace("%afk_point%", String.valueOf(point))));
                    }
                    return false;
                }
                if(args[0].equals("차감")) {
                    afkPlayer.removePoint(point);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("RemoveMessage.ForAdmin", "")
                            .replace("%afk_player%", targetPlayer.getName()).replace("%afk_point%", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = targetPlayer.getPlayer();
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("RemoveMessage.ForUser", "")
                                .replace("%afk_point%", String.valueOf(point))));
                    }
                    return false;
                }
                if(args[0].equals("설정")) {
                    afkPlayer.setPoint(point);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("SetMessage.ForAdmin", "")
                            .replace("%afk_player%", targetPlayer.getName()).replace("%afk_point%", String.valueOf(point))));
                    if(targetPlayer.isOnline()) {
                        Player onlineTarget = (Player) targetPlayer;
                        onlineTarget.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("SetMessage.ForUser", "")
                                .replace("%afk_point%", String.valueOf(point))));
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
            AfkPlayer afkPlayer = AfkAPI.getPlayer(targetPlayer);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("SearchMessage.ForAdmin", "")
                    .replace("%afk_player%", targetPlayer.getName()).replace("%afk_point%", String.valueOf(afkPlayer.getPoint()))));
            return false;
        }
        if(args[0].equals("리로드")) {
            AfkCore.instance.reloadConfig();
            AfkCore.cf = AfkCore.instance.getConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("ReloadMessage")));
            return false;
        }
        if(args[0].equals("저장")) {
            PlayerData.save();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("SaveMessage")));
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
        List<String> subCommands = Arrays.asList("지급", "차감", "설정", "확인", "리로드", "저장");
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
