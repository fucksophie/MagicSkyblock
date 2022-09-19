package lv.pi.MagicSkyblock.features;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class Utils {
    public static List<Location> getArenaBlocks(Location l, int radius) {
        World w = l.getWorld();
        int xCoord = (int) l.getX();
        int zCoord = (int) l.getZ();
        int YCoord = (int) l.getY();
    
        List<Location> tempList = new ArrayList<Location>();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -radius; y <= radius; y++) {
                    tempList.add(new Location(w, xCoord + x, YCoord + y, zCoord + z));
                }
            }
        }
        return tempList;
    }

    public static Double randomNumber() {
        return new BigDecimal(Math.random()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static Double randomNumber(Integer scaled) {
        return new BigDecimal(Math.random()).setScale(scaled, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static ItemStack bottleItemstack() {
        ItemStack bottle = new ItemStack(Material.POTION, 1);
        ItemMeta meta = bottle.getItemMeta();
        PotionMeta pmeta = (PotionMeta) meta;
        PotionData pdata = new PotionData(PotionType.WATER);
        pmeta.setBasePotionData(pdata);
        bottle.setItemMeta(meta);
        return bottle;
    }
    public static void looseMatch(String match, Iterator<Recipe> it) {
        Recipe recipe;
        
        while(it.hasNext()) {
            recipe = it.next();
            if(recipe == null) continue;
            ItemMeta meta = recipe.getResult().getItemMeta();
            if(meta == null) continue;

            if (meta.getDisplayName().contains(match)) it.remove();
        }
    }
    
    public static void exactMatch(Material match, Iterator<Recipe> it) {
        Recipe recipe;
        
        while(it.hasNext()) {
            recipe = it.next();
            if(recipe == null) continue;

            if (recipe.getResult().getType() == match) it.remove();
        }
    }
}
