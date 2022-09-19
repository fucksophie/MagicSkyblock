package lv.pi.MagicSkyblock.features.grass;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import lv.pi.MagicSkyblock.features.Utils;

public class GrassBreaking implements Listener {
    static class SeedType {
        Double chance;
        Material seed;

        public SeedType(Double chance, Material seed) {
            this.chance = chance;
            this.seed = seed;
        }
    }
    
    public static ArrayList<SeedType> seeds = new ArrayList<SeedType>();

    static {
        seeds.clear();
        seeds.add(new SeedType(0.005, Material.WHEAT_SEEDS));
        seeds.add(new SeedType(0.003, Material.PUMPKIN_SEEDS));
        seeds.add(new SeedType(0.002, Material.MELON_SEEDS));
        seeds.add(new SeedType(0.001, Material.BEETROOT_SEEDS));
        seeds.add(new SeedType(0.001, Material.WHEAT));
    }

    @EventHandler
    public void onBreakEvent(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(block.getType().equals(Material.GRASS) || block.getType().equals(Material.TALL_GRASS)) {
            Location loc = block.getLocation();
            Location centerOfBlock = loc.add(0.5, 0.5, 0.5);

            event.setExpToDrop(1);
            event.setDropItems(false);
            
            for(Integer x = 0; x < seeds.size()-1; x++) {
                SeedType t = seeds.get(x);
                if(Utils.randomNumber() <= (block.getType().equals(Material.TALL_GRASS) ? t.chance+0.01 : t.chance)) {
                    event.getBlock().getWorld().dropItemNaturally(centerOfBlock, new ItemStack(t.seed));
                    break;
                }
            }
        }
    }
}
