package net.ehaqui.ehchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class Utils {
    public static Object[] playersAround(Player player, int radius) {
        Object[] allplayers = Bukkit.getOnlinePlayers ().toArray ();
        List<Player> playersfound = null;
        for (Object playerine: allplayers) {
            if (((Player) playerine).getLocation ().distance (player.getLocation ()) <= radius&&((Player) playerine).getUniqueId () != player.getUniqueId ()) {
                playersfound.add (((Player) playerine));
            }
        }
        if (playersfound == null) {
            return null;
        } else {
            return playersfound.toArray ();
        }
    }
}
