package net.ehaqui.ehchat.events;


import net.ehaqui.ehcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chat implements Listener {
    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
        String formatedname = Utils.NameStruct (event.getPlayer ());
        event.setCancelled (true);
        Object[] chats = Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getStringList ("chats.custom").toArray ();
        if (event.getMessage ().length () == 1) {
            net.ehaqui.ehchat.utils.Utils.Local (event.getPlayer (), 100, event.getMessage (), formatedname);
            return;
        }
        for (Object chat : chats) {
            if (Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".prefix") != null) {
                if (event.getMessage ().charAt (0) == Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".prefix").charAt (0)) {
                    if (Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".permission.send") != null) {
                        if (event.getPlayer ().hasPermission (Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".permission.send"))) {
                            String message;
                            message = Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".format");
                            message = Utils.formatColor (message.replace ("$NICK", formatedname));
                            message = message.replace ("$MSG", event.getMessage ().substring (1));
                            for (Player entity : Bukkit.getOnlinePlayers ()) {
                                if (entity.hasPermission (Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".permission.see"))) {
                                    entity.sendRawMessage (message);
                                }
                            }
                            return;
                        } else {
                            net.ehaqui.ehchat.utils.Utils.Local (event.getPlayer (), 100, event.getMessage (), formatedname);
                            return;
                        }
                    } else {
                        ChatCustom (event, formatedname, chat);
                        return;
                    }
                }

            }
        }
        net.ehaqui.ehchat.utils.Utils.Local (event.getPlayer (), 100, event.getMessage (), formatedname);
    }

    private void ChatCustom(AsyncPlayerChatEvent event, String formatedname, Object chat) {
        String message;
        message = Bukkit.getPluginManager ().getPlugin ("EhChat").getConfig ().getString ("chats." + chat + ".format");
        message = Utils.formatColor (message.replace ("$NICK", formatedname));
        message = message.replace ("$MSG", event.getMessage ().substring (1));
        Bukkit.broadcastMessage (message);
    }

}
