package lv.pi.MagicSkyblock.features.eggtomob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import lv.pi.MagicSkyblock.features.Utils;

public class EggToMob implements Listener {

    static class MobType {
        Double chance;
        EntityType entityType;

        public MobType(Double chance, EntityType entityType) {
            this.chance = chance;
            this.entityType = entityType;
        }
    }

    public static ArrayList<MobType> types = new ArrayList<MobType>();

    static {
        types.clear();
        types.add(new MobType(0.005, EntityType.COW));
        types.add(new MobType(0.003, EntityType.MUSHROOM_COW));
        types.add(new MobType(0.002, EntityType.PIG));
        types.add(new MobType(0.002, EntityType.SHEEP));

        types.add(new MobType(0.002, EntityType.RABBIT));
        types.add(new MobType(0.001, EntityType.WOLF));
        types.add(new MobType(0.001, EntityType.CAT));
        types.add(new MobType(0.0001, EntityType.SILVERFISH));
    }


    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        for(Integer x = 0; x < types.size()-1; x++) {
            MobType t = types.get(x);
            if(Utils.randomNumber() <= t.chance) {
                event.getEgg().getWorld().spawnEntity(event.getEgg().getLocation(), t.entityType);
                break;
            }
        }
    }
}
