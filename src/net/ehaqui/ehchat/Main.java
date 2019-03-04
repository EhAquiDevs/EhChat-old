package net.ehaqui.ehchat;

import net.ehaqui.ehchat.events.chat;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onLoad() {
        this.getLogger ().info ("Carregado!");
    }

    @Override
    public void onEnable() {
        this.getLogger ().info ("Ativando...");
        this.getLogger ().info ("Registrando eventos...");
        this.getServer ().getPluginManager ().registerEvents (new chat (), this);
        this.getLogger ().info ("OK!");
    }

    @Override
    public void onDisable() {
        this.getLogger ().info ("Desativado!");
    }

}
