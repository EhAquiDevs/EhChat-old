package net.ehaqui.ehchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
    public static void Local(Player player, int radius, String message, String nick) {
        int yes = 0;
        for (Player entity : Bukkit.getOnlinePlayers ()) {
            if (entity.getLocation ().distance (player.getLocation ()) <= radius && entity.getUniqueId () != player.getUniqueId ()) {
                yes = +1;
            }
        }
        if (yes != 0) {
            for (Player entity : Bukkit.getOnlinePlayers ()) {
                if (entity.getLocation ().distance (player.getLocation ()) <= radius) {
                    entity.sendRawMessage (net.ehaqui.ehcore.utils.Utils.formatColor (nick + "&7 :&r ") + message);
                }
            }
        } else {
            player.sendRawMessage ("Não há ninguem perto de você...");
        }
    }
}
