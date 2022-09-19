package lv.pi.MagicSkyblock.features.darkwater;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lv.pi.MagicSkyblock.MagicSkyblock;
import lv.pi.MagicSkyblock.features.Utils;

public class Darkwater implements Listener {
    public static HashMap<UUID, PlayerDarkwater> darkwaters = new HashMap<UUID, PlayerDarkwater>();


    @EventHandler(priority = EventPriority.HIGHEST) // should fix discordsrv idk
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        
        Integer tier = DarkwaterSystem.getDarkwater(player.getUniqueId());

        if(tier >= 1) { 
            event.setMessage("(drunk on Darkwater x"+tier.toString()+") " + shuffle(event.getMessage()));
        }

    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (event.getItem().getItemMeta().getDisplayName().contains("Dark")) {
            if(MagicSkyblock.ms.removeMana(player.getUniqueId(), 8)) {
                Bukkit.broadcastMessage("§5§l > §f"+player.getName() + " inked da sussy water..");
                DarkwaterSystem.addDarkwater(player.getUniqueId(), 1, 0);
            } else {
                event.setCancelled(true);
            }
        }
    }
    public static void enable(JavaPlugin plugin) {
        Utils.looseMatch("Dark", plugin.getServer().recipeIterator());
        ItemStack darkwater = Utils.bottleItemstack();

        ItemMeta m = darkwater.getItemMeta();
        m.setDisplayName("§8§k§lDark §r§7§lWater");
        darkwater.setItemMeta(m);

        plugin.getServer().addRecipe(new FurnaceRecipe(NamespacedKey.fromString("melon:darkwater"), darkwater, Material.GLASS_BOTTLE, 2, 300));
    }

    public static void tick(Player p) {
        Integer tier = DarkwaterSystem.getDarkwater(p.getUniqueId());

        if(tier >= 1) { 
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, tier, false, false));

            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 22, tier, false, false));
            p.setSaturation(20);
            p.setFoodLevel(p.getFoodLevel() + 4);
            
            DarkwaterSystem.addDarkwater(p.getUniqueId(), 0, 1);

            if(DarkwaterSystem.getDarkwaterTime(p.getUniqueId()) >= 60) {
                DarkwaterSystem.setDarkwater(p.getUniqueId(), 0, 0);
                Bukkit.broadcastMessage("§5§l > §f"+p.getName() + " has recovered from the effects of darkwater!");

            }
        }
    }


    public static String shuffle(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        double rnd;
        for (char c: string.toCharArray()) {
            rnd = Math.random();
            if (rnd < 0.34)
                sb.append(c);
            else if (rnd < 0.67)
                sb.insert(sb.length() / 2, c);
            else
                sb.insert(0, c);
        }       
        return sb.toString();
    }

    
    
}