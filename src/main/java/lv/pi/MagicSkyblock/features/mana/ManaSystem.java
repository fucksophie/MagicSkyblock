package lv.pi.MagicSkyblock.features.mana;

import java.util.HashMap;
import java.util.UUID;

public class ManaSystem {
    private HashMap<UUID, PlayerMana> manas = new HashMap<UUID, PlayerMana>();

    public Integer getMana(UUID uuid) {
        if(!manas.containsKey(uuid)) {
            manas.put(uuid, new PlayerMana());
        }

        return manas.get(uuid).mana;
    }

    public boolean addMana(UUID uuid, Integer mana) {
        PlayerMana pmana = manas.get(uuid);
        pmana.mana += mana;

        if(pmana.mana > pmana.maxMana) {
            pmana.mana = pmana.maxMana;
            this.setMana(uuid, pmana.mana);
            return false;
        }

        this.setMana(uuid, pmana.mana);
        return true;
    }
    

    public boolean removeMana(UUID uuid, Integer mana) {
        PlayerMana pmana = manas.get(uuid);
        if(mana > pmana.mana) {
            return false;
        }
        
        pmana.mana -= mana;

        if(pmana.mana < 0) {
            pmana.mana = 0;
        }

        this.setMana(uuid, pmana.mana);
        return true;
    }

    public void setMana(UUID uuid, Integer mana) {
        PlayerMana pmana = manas.get(uuid);
        pmana.mana = mana;

        manas.put(uuid, pmana);
    }

    public Integer getMaxMana(UUID uuid) {
        return manas.get(uuid).maxMana;
    }

    public void setMaxMana(UUID uuid, Integer maxMana) {
        PlayerMana mana = manas.get(uuid);
        mana.maxMana = maxMana;

        manas.put(uuid, mana);
    }

}
