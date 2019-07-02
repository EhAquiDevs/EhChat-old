package net.ehaqui.ehchat.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.ehaqui.ehcore.utils.Utils.formatColor;

public class Utils {
    public static void Local(Player player, int radius, String message, String nick) {

        List<Entity> entities = player.getNearbyEntities(radius, radius, radius);
        List<Player> players = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity instanceof Player) {
                players.add((Player) entity);
            }
        }

        if (players.size() == 0) {
            player.sendRawMessage ("Ninguém escuta você...");
            return;
        }

        for (Player p : players) {

            if (!(p.hasPermission("chats.local.permission.see"))) {
                continue;
            }
            p.sendMessage(formatColor(nick + "&7:&r ") + message);


        }
        player.sendMessage(formatColor(nick + "&7:&r ") + message);



    }

    public static void privado(Player sender, Player receiver, String message) {

        /*if (!(receiver.hasPermission("chats.privado.permission.see"))) {
            return;
        }

        if (!(sender.hasPermission("chats.privado.permission.send"))) {
            return;
        }*/

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
        sender.sendMessage(messageSend);
    }

}
