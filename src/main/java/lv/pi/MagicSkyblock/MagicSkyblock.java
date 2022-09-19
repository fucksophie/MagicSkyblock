package lv.pi.MagicSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lv.pi.MagicSkyblock.features.cobblegen.CustomCobbleGen;
import lv.pi.MagicSkyblock.features.conversion.Conversion;
import lv.pi.MagicSkyblock.features.darkwater.Darkwater;
import lv.pi.MagicSkyblock.features.eggtomob.EggToMob;
import lv.pi.MagicSkyblock.features.grass.GrassBreaking;
import lv.pi.MagicSkyblock.features.grass.GrassGrowing;
import lv.pi.MagicSkyblock.features.grass.GrassSneaking;
import lv.pi.MagicSkyblock.features.mana.ManaSystem;
import lv.pi.MagicSkyblock.features.wands.Wands;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class MagicSkyblock extends JavaPlugin {
    static Integer globalTaskID;
    public static ManaSystem ms = new ManaSystem();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new CustomCobbleGen(), this);
        this.getServer().getPluginManager().registerEvents(new Darkwater(), this);
        this.getServer().getPluginManager().registerEvents(new Conversion(), this);
        this.getServer().getPluginManager().registerEvents(new Wands(), this);
        this.getServer().getPluginManager().registerEvents(new GrassSneaking(), this);
        this.getServer().getPluginManager().registerEvents(new GrassBreaking(), this);
        this.getServer().getPluginManager().registerEvents(new EggToMob(), this);

        Darkwater.enable(this);
        Conversion.enable(this);
        Wands.enable(this);
        globalTaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§5§l✯" + ms.getMana(p.getUniqueId())));
                    ms.addMana(p.getUniqueId(), 1);

                    GrassGrowing.tick(p);
                    Darkwater.tick(p);
                });
            }
        }, 0, 20);

        Bukkit.broadcastMessage("§5§l > MS enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");

        // TODO: Figure out if this whole globalTasKID thing is really needed
        if(globalTaskID != null) Bukkit.getScheduler().cancelTask(globalTaskID);
    }
}