package lv.pi.MagicSkyblock.features.conversion;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import lv.pi.MagicSkyblock.MagicSkyblock;
import lv.pi.MagicSkyblock.features.Utils;

public class Conversion implements Listener {
    
    public static ArrayList<InteractElement> interactables = new ArrayList<InteractElement>();

    public static void enable(JavaPlugin plugin) {
        Utils.exactMatch(Material.LAVA_BUCKET, plugin.getServer().recipeIterator());
        Utils.exactMatch(Material.LEATHER, plugin.getServer().recipeIterator());

        plugin.getServer().addRecipe(
            new FurnaceRecipe(NamespacedKey.fromString("melon:lava"), new ItemStack(Material.LAVA_BUCKET), Material.IRON_INGOT, 2, 200)
        );

        plugin.getServer().addRecipe(
            new FurnaceRecipe(NamespacedKey.fromString("melon:leather"), new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 2, 200)
        );
        Conversion.interactables.add(new InteractElement(Material.DIRT, Material.GRASS_BLOCK, 3));
        Conversion.interactables.add(new InteractElement(Material.GRASS_BLOCK, Material.PODZOL, 3));
        Conversion.interactables.add(new InteractElement(Material.PODZOL, Material.GRAVEL, 5));
        Conversion.interactables.add(new InteractElement(Material.GRAVEL, Material.SAND, 5));
        Conversion.interactables.add(new InteractElement(Material.COBBLESTONE, Material.DIRT, 5));
        Conversion.interactables.add(new InteractElement(Material.TALL_GRASS, Material.MOSS_BLOCK, 5));
        Conversion.interactables.add(new InteractElement(Material.GLASS, Material.TINTED_GLASS, 5));
    }

    static class InteractElement {
        Material from;
        Material to;
        Integer cost;
        public InteractElement(Material from, Material to, Integer cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Material type = player.getInventory().getItemInMainHand().getType();

        if (action.equals(Action.RIGHT_CLICK_BLOCK) && player.isSneaking() 
        && (type == Material.AIR || type == Material.STICK)) {
            Conversion.interactables.forEach(i -> {
                if(i.from.equals(block.getType())) {
                    if(MagicSkyblock.ms.removeMana(player.getUniqueId(), i.cost)) {
                        block.setType(Material.BEDROCK);
                        block.getState().update(true);

                        new BukkitRunnable() {
                            public void run() {
                                block.setType(i.to);
                                block.getState().update(true);
                            }
                        }.runTaskLater(JavaPlugin.getPlugin(MagicSkyblock.class), 20); 
                    }
                }
            });
        }
    }
}
