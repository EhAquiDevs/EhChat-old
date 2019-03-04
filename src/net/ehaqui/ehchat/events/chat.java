package net.ehaqui.ehchat.events;


import net.ehaqui.ehcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chat implements Listener {
    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
        String formatedname = Utils.NameStruct (event.getPlayer ());
        event.setCancelled (true);
        Bukkit.broadcastMessage (Utils.formatColor (formatedname + "&7: " + event.getMessage ()));
    }

}
