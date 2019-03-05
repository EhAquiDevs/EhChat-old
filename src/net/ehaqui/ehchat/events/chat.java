package net.ehaqui.ehchat.events;


import net.ehaqui.ehcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.List;

public class chat implements Listener {
    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
        String formatedname = Utils.NameStruct (event.getPlayer ());
        event.setCancelled (true);
        Object[] chats = Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getStringList ("chats.custom").toArray();
        if (event.getMessage ().length () == 1) {
            Object[] players = net.ehaqui.ehchat.utils.Utils.playersAround (event.getPlayer (), Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getInt ("chats.Local.prefix"));
            if (players != null) {
                event.getPlayer ().sendRawMessage (Utils.formatColor (formatedname + "&7:&r ") + event.getMessage ());
                for (Object theplayer: players) {
                    ((Player) theplayer).sendRawMessage (Utils.formatColor (formatedname + "&7:&r ") + event.getMessage ());
                }
                return;
            } else {event.getPlayer ().sendRawMessage ("Não há ninguem perto de você...");return;}
        }
        for (Object chat: chats) {
           if (Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getString("chats." + chat + ".prefix") != null) {
               if (event.getMessage ().charAt (0) == Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getString("chats." + chat + ".prefix").charAt (0)) {
                   if (Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getInt ("chats." + chat + ".radius") == 0) {
                       String message;
                       message = Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getString("chats." + chat + ".format");
                       message = Utils.formatColor(message.replace("$NICK", formatedname));
                       message = message.replace ("$MSG", event.getMessage ().substring (1));
                       Bukkit.broadcastMessage (message);
                       return;
                   }
               }

           }
        }

        Object[] players = net.ehaqui.ehchat.utils.Utils.playersAround (event.getPlayer (), Bukkit.getPluginManager ().getPlugin("EhChat").getConfig ().getInt ("chats.Local.prefix"));
        if (players != null) {
            event.getPlayer ().sendRawMessage (Utils.formatColor (formatedname + "&7:&r ") + event.getMessage ());
            for (Object theplayer: players) {
                ((Player) theplayer).sendRawMessage (Utils.formatColor (formatedname + "&7:&r ") + event.getMessage ());
            }
            return;
        } else {event.getPlayer ().sendRawMessage ("Não há ninguem perto de você...");return;}
    }

}
