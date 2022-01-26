package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.slimefun.types.RefillableUseItem;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class LuminescenceScoop extends RefillableUseItem {

    private static final NamespacedKey key = Keys.newKey("uses");

    @ParametersAreNonnullByDefault
    public LuminescenceScoop(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(itemGroup, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            if (e.getPlayer().isSneaking()) {
                removeLight(e);
            } else {
                setLight(e);
            }
        };
    }

    private void removeLight(PlayerRightClickEvent event) {
        final Player player = event.getPlayer();
        final Location start = player.getEyeLocation();
        final Vector direction = start.getDirection();
        for (int i = 1; i < 6; i++) {
            final Block checkBlock = start.add(direction.multiply(i)).getBlock();
            if (checkBlock.getType() == Material.LIGHT
                && GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK)
            ) {
                checkBlock.setType(Material.AIR);
                refillItem(event.getItem());
            }
        }
    }

    private void setLight(PlayerRightClickEvent event) {
        if (event.getClickedBlock().isPresent()) {
            Block block = event.getClickedBlock().get();
            Block possibleLight = block.getRelative(event.getClickedFace());
            if (possibleLight.isEmpty()
                && GeneralUtils.hasPermission(event.getPlayer(), possibleLight, Interaction.BREAK_BLOCK)
            ) {
                possibleLight.setType(Material.LIGHT);
                damageItem(event.getPlayer(), event.getItem());
            }
        }
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }
}
