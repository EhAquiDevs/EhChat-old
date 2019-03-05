package net.ehaqui.ehchat;

import net.ehaqui.ehchat.events.chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    @Override
    public void onLoad() {
        this.getLogger ().info ("Carregado!");
    }

    @Override
    public void onEnable() {
        this.getLogger ().info ("Ativando...");
        if (!new File (getDataFolder (), "config.yml").exists ()) {
            this.saveDefaultConfig ();
            Bukkit.getServer ().getPluginManager ().getPlugin ("EhCore").getLogger ().info ("Config não foi encontrada, portanto, uma foi criada.");
        } else {
            Bukkit.getServer ().getPluginManager ().getPlugin ("EhCore").getLogger ().info("Config OK");
        }
        this.getLogger ().info ("Registrando eventos...");
        this.getServer ().getPluginManager ().registerEvents (new chat (), this);
        this.getLogger ().info ("OK!");
    }

    @Override
    public void onDisable() {
        this.getLogger ().info ("Desativado!");
    }

}
