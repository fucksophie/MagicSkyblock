package lv.pi.MagicSkyblock.features.grass;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import lv.pi.MagicSkyblock.features.Utils;

public class GrassSneaking implements Listener {
    @EventHandler(ignoreCancelled = true)
    private void onPlayerSneak(PlayerToggleSneakEvent event) {        
        Utils.getArenaBlocks(event.getPlayer().getLocation(), 5).stream()
            .filter(($) -> ThreadLocalRandom.current().nextFloat() < (float)10 / 100.0F)
            .forEach((x) -> this.bonemeal(x.getBlock()));
    }

    private void bonemeal(Block block) {
        BlockData ageable = block.getState().getBlockData();
        if (ageable instanceof Sapling) {
            Sapling sapling = (Sapling)ageable;
            if (sapling.getStage() >= sapling.getMaximumStage()) {
                IntStream.range(0, 10)
                    .forEach(($) -> block.applyBoneMeal(BlockFace.UP));
            } else {
                block.applyBoneMeal(BlockFace.UP);
            }
        }
    }
}
