package lv.pi.MagicSkyblock.features.wands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import lv.pi.MagicSkyblock.MagicSkyblock;
import lv.pi.MagicSkyblock.features.Utils;
import lv.pi.MagicSkyblock.features.darkwater.DarkwaterSystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Wands implements Listener {
    public static void enable(JavaPlugin plugin) {
        Utils.looseMatch("Wand", plugin.getServer().recipeIterator());
        
        ItemStack nature = new ItemStack(Material.STICK);
        ItemMeta natureMeta = nature.getItemMeta();
        natureMeta.setDisplayName("§a§lWand of §2§lNature");
        natureMeta.setLore(Arrays.asList("§r§2This Wand allows", "§r§2you to convert leaves!"));
        nature.setItemMeta(natureMeta);


        ItemStack mana = new ItemStack(Material.STICK);
        ItemMeta manaMeta = mana.getItemMeta();
        manaMeta.setDisplayName("§d§lWand of §5§lMana");
        manaMeta.setLore(Arrays.asList("§r§5This wand allows you to", "§r§5exchange life for mana!"));
        mana.setItemMeta(manaMeta);

        ShapedRecipe natureRecipe = new ShapedRecipe(NamespacedKey.fromString("melon:wand_of_nature"), nature);
        ShapedRecipe manaRecipe = new ShapedRecipe(NamespacedKey.fromString("melon:wand_of_mana"), mana);
        
        natureRecipe.shape("pos", "ota", "sat");

        natureRecipe.setIngredient('p', Material.APPLE);
        natureRecipe.setIngredient('o', Material.OAK_SAPLING);
        natureRecipe.setIngredient('s', Material.STRING);
        natureRecipe.setIngredient('t', Material.STICK);
        natureRecipe.setIngredient('a', Material.AIR);
        plugin.getServer().addRecipe(natureRecipe);
        
        manaRecipe.shape("ryw","ysa","was");

        manaRecipe.setIngredient('r', Material.RED_DYE);
        manaRecipe.setIngredient('y', Material.YELLOW_DYE);
        manaRecipe.setIngredient('w', Material.GLASS_BOTTLE);
        manaRecipe.setIngredient('s', Material.STICK);
        manaRecipe.setIngredient('a', Material.AIR);
        plugin.getServer().addRecipe(manaRecipe);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack holding = player.getInventory().getItemInMainHand();
            if(holding == null) return;
            ItemMeta meta = holding.getItemMeta();
            if(meta == null) return;
            String name = meta.getDisplayName();

            if(name.equals("§d§lWand of §5§lMana")) {
                if(MagicSkyblock.ms.addMana(player.getUniqueId(), 1)) {
                    if(DarkwaterSystem.getDarkwater(player.getUniqueId()) < 1) {
                        player.damage(4);
                    } else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§d§l The dark water shields you."));
                    }
                }
            } else if(name.equals("§a§lWand of §2§lNature")) {
                Block bloc = player.getTargetBlock(null, 100);
                Location loc = bloc.getLocation();
                Location centerOfBlock = loc.add(0.5, 0.5, 0.5);

                if(bloc.getType() == Material.OAK_LOG) {
                    bloc.setType(Material.AIR);
                    bloc.getState().update(true);
                    bloc.getWorld().dropItemNaturally(centerOfBlock, new ItemStack(Material.OAK_LOG));
                }
                if(bloc.getType() == Material.OAK_LEAVES) {
                    bloc.setType(Material.AIR);
                    bloc.getState().update(true);
                    if(Utils.randomNumber() <= 0.10) {
                        bloc.getWorld().dropItemNaturally(centerOfBlock, new ItemStack(Material.OAK_SAPLING));
                        return;   
                    }
                    if(Utils.randomNumber() <= 0.05) {
                        bloc.getWorld().dropItemNaturally(centerOfBlock, new ItemStack(Material.APPLE));
                        return;   
                    }
                    if(Utils.randomNumber() <= 0.02) {
                        bloc.getWorld().dropItemNaturally(centerOfBlock, new ItemStack(Material.STRING));
                        return;   
                    }
                }
            }
        }
    }
}
