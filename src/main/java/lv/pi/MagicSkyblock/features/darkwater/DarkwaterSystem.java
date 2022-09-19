package lv.pi.MagicSkyblock.features.darkwater;

import java.util.HashMap;
import java.util.UUID;

public class DarkwaterSystem {
    public static HashMap<UUID, PlayerDarkwater> darkwaters = new HashMap<UUID, PlayerDarkwater>();

    public static Integer getDarkwater(UUID uuid) {
        if(!darkwaters.containsKey(uuid)) {
            darkwaters.put(uuid, new PlayerDarkwater());
        }

        return darkwaters.get(uuid).amount;
    }
    public static Integer getDarkwaterTime(UUID uuid) {
        if(!darkwaters.containsKey(uuid)) {
            darkwaters.put(uuid, new PlayerDarkwater());
        }

        return darkwaters.get(uuid).time;
    }
    public static boolean addDarkwater(UUID uuid, Integer darkwater, Integer time) {
        PlayerDarkwater pDarkwater = darkwaters.get(uuid);
        pDarkwater.amount += darkwater;
        pDarkwater.time += time;

        DarkwaterSystem.setDarkwater(uuid, pDarkwater.amount, pDarkwater.time);
        return true;
    }
    
    public static void setDarkwater(UUID uuid, Integer darkwater, Integer time) {
        PlayerDarkwater pDarkwater = darkwaters.get(uuid);
        pDarkwater.amount = darkwater;
        pDarkwater.time = time;

        darkwaters.put(uuid, pDarkwater);
    }
}