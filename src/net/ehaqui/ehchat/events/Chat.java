package net.ehaqui.ehchat.events;


import net.ehaqui.ehchat.Main;
import net.ehaqui.ehcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    private Main plugin;

    public Chat(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        String formatedname = Utils.NameStruct(player);
        event.setCancelled (true);

        FileConfiguration config = plugin.getConfig();

        Object[] chats = config.getStringList("chats.custom").toArray();

        if (event.getMessage().length() == 1 && player.hasPermission("chats.local.permission.send")) {
            net.ehaqui.ehchat.utils.Utils.Local (player, 100, event.getMessage (), formatedname);
            return;
        }

        if (event.getMessage().charAt(0) == '#') {
            Player destino = Bukkit.getPlayer(event.getMessage().split(" ")[0].substring(1));
            if (destino == null) {
                player.sendMessage(ChatColor.RED + "O player não está online ou não existe.");
                return;
            }
            net.ehaqui.ehchat.utils.Utils.privado(player, destino, event.getMessage());
            return;
        }

        for (Object chat : chats) {

            if (config.getString ("chats." + chat + ".prefix") == null) {
                continue;
            }

            if (event.getMessage().charAt(0) == config.getString ("chats." + chat + ".prefix").charAt(0)) {

                if (player.hasPermission("chats." + chat + ".permission.send")) {
                    String message;
                    message = config.getString ("chats." + chat + ".format");
                    message = Utils.formatColor(message.replace("$NICK", formatedname));
                    message = message.replace("$MSG", event.getMessage().substring(1).trim());

                    for (Player entity : Bukkit.getOnlinePlayers ()) {
                        if (entity.hasPermission ("chats." + chat + ".permission.see")) {
                            entity.sendMessage(message);
                        }
                    }

                    return;

                } else if (player.hasPermission("chats.local.permission.send")) {
                    net.ehaqui.ehchat.utils.Utils.Local (player, 100, event.getMessage (), formatedname);
                    return;
                }
            }
        }
        if (player.hasPermission("chats.local.permission.send")) {
            net.ehaqui.ehchat.utils.Utils.Local (player, 100, event.getMessage (), formatedname);
        }

    }

}
