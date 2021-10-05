package com.stuudent.afk.schedulers;

import com.stuudent.afk.AfkAPI;
import com.stuudent.afk.AfkCore;
import com.stuudent.afk.enums.TitleType;
import com.stuudent.afk.event.AfkEnableEvent;
import com.stuudent.afk.interfaces.AfkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AfkScheduler implements Runnable{

    @Override
    public void run() {
        Bukkit.getScheduler().runTaskAsynchronously(AfkCore.instance, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                AfkPlayer afkPlayer = AfkAPI.getPlayer(player);
                if(afkPlayer.isEnabled()) {
                    afkPlayer.increaseTime();
                    if(afkPlayer.getTime() % AfkCore.cf.getInt("AFKPeriod", 60) == 0) {
                        afkPlayer.addPoint(1);
                    }
                    player.sendTitle(afkPlayer.getTitleFormat(TitleType.ENABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 30, 0);
                } else {
                    if(afkPlayer.isReady()) {
                        AfkEnableEvent event = new AfkEnableEvent(player);
                        Bukkit.getPluginManager().callEvent(event);
                        if(event.isCancelled()) return;
                        afkPlayer.resetReadyTime();
                        afkPlayer.setState(true);
                        player.sendTitle(afkPlayer.getTitleFormat(TitleType.ENABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 20, 30, 0);
                    } else {
                        afkPlayer.increaseReadyTime();
                    }
                }
            }
        });
    }

}
