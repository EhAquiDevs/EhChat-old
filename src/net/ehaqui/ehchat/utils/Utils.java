package net.ehaqui.ehchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
    public static void Local(Player player, int radius, String message, String nick) {

        boolean sentMessage = false;

        for (Player entity : Bukkit.getOnlinePlayers ()) {
            if (!(entity.hasPermission("chats.local.permission.see"))) {
                continue;
            }
            if (entity.getLocation().distance(player.getLocation ()) <= radius && entity.getUniqueId() != player.getUniqueId()) {
                entity.sendMessage(net.ehaqui.ehcore.utils.Utils.formatColor (nick + "&7 :&r ") + message);
                sentMessage = true;
            }
        }

        if (!sentMessage) {
            player.sendRawMessage ("Não há ninguem perto de você...");
        }

    }
}
