package lv.pi.MagicSkyblock.features.cobblegen;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import lv.pi.MagicSkyblock.features.Utils;

public class CustomCobbleGen implements Listener {
    static class GenType {
        Double chance;
        Material material;

        public GenType(Double chance, Material material) {
            this.chance = chance;
            this.material = material;
        }
    }

    public static ArrayList<GenType> types = new ArrayList<GenType>();

    static {
        types.clear();
        types.add(new GenType(0.30, Material.COAL_ORE));
        types.add(new GenType(0.17, Material.STONE));
        types.add(new GenType(0.14, Material.IRON_ORE));
        types.add(new GenType(0.10, Material.GOLD_ORE));
        types.add(new GenType(0.09, Material.LAPIS_ORE));
        types.add(new GenType(0.08, Material.REDSTONE_ORE));
        types.add(new GenType(0.07, Material.NETHER_QUARTZ_ORE));
        types.add(new GenType(0.02, Material.DIAMOND_ORE));
        types.add(new GenType(0.01, Material.EMERALD_ORE));

    }


    @EventHandler
	public void onFromTo(BlockFormEvent event) {
        Block b = event.getBlock();
		if (b != null) {
            if(!b.getType().toString().toUpperCase().contains("LAVA")) return; 

            event.setCancelled(true);

            Boolean found = false;

            for(Integer x = 0; x < types.size()-1; x++) {
                GenType t = types.get(x);
                if(Utils.randomNumber() <= t.chance) {
                    found = true;
                    b.setType(t.material);
                    b.getState().update(true);
                    break;
                }
            }
            
            if(!found) {
                b.setType(Material.COBBLESTONE);
                b.getState().update(true);
            }
		}
	}

}


