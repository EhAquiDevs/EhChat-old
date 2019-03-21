package net.ehaqui.ehchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static net.ehaqui.ehcore.utils.Utils.formatColor;

public class Utils {
    public static void Local(Player player, int radius, String message, String nick) {

        boolean sentMessage = false;

        for (Player entity : Bukkit.getOnlinePlayers ()) {
            if (!(entity.hasPermission("chats.local.permission.see"))) {
                continue;
            }
            if (entity.getLocation().distance(player.getLocation ()) <= radius) {
                entity.sendMessage(formatColor(nick + "&7 :&r ") + message);
                sentMessage = true;
            }
        }

        if (!sentMessage) {
            player.sendRawMessage ("Não há ninguem perto de você...");
        }

    }

    public static void privado(Player sender, Player receiver, String message) {

        if (!(receiver.hasPermission("chats.privado.permission.see"))) {
            return;
        }

        if (!(sender.hasPermission("chats.privado.permission.send"))) {
            return;
        }

        if (sender.getUniqueId() == receiver.getUniqueId()) {
            sender.sendMessage(ChatColor.RED + "Você não pode mandar uma mensagem para si mesmo!");
            return;
        }

        String senderName = sender.getDisplayName();
        String receiverName = receiver.getDisplayName();
        String[] messageArray = message.split(" ");
        String messageSend = String.join(" ", Arrays.copyOfRange(messageArray, 1, messageArray.length));

        messageSend = formatColor(ChatColor.DARK_PURPLE + senderName + " → " + receiverName + " "
                + ChatColor.LIGHT_PURPLE + ":" + ChatColor.DARK_PURPLE + " ") + messageSend;
        messageSend = messageSend.trim();
        receiver.sendMessage(messageSend);
    }

}
