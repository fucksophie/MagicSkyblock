package lv.pi.MagicSkyblock.features.grass;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import lv.pi.MagicSkyblock.features.Utils;

public class GrassGrowing {
    public static void tick(Player p) {
        Utils.getArenaBlocks(p.getLocation(), 6).forEach(b -> {
            Material material = b.getBlock().getType();
            if(material == Material.GRASS_BLOCK || material == Material.MOSS_BLOCK || material == Material.PODZOL) {
                b.add(0, 1, 0);
                Block above = b.getBlock();
                if(above.getType() == Material.AIR) {
                    if(Math.random() < 0.0001) {

                        above.setType(Material.GRASS);
                        above.getState().update(true);
                        return;
                    }
                }
            } else if(material == Material.GRASS) {
                if(Math.random() < 0.0002) {
                    Block bottom = b.getBlock();
                    b.add(0, 1, 0);
                    Block top = b.getBlock();
                    if(top.getType() == Material.AIR) {
                        top.setType(Material.TALL_GRASS);
                        top.setBlockData(Bukkit.createBlockData("minecraft:tall_grass[half=upper]"));
                        top.getState().update(true);
                        
                        bottom.setType(Material.TALL_GRASS);
                        bottom.setBlockData(Bukkit.createBlockData("minecraft:tall_grass[half=lower]"));
                        bottom.getState().update(true);
                    }
    
                }
            }
        });
    }
}
