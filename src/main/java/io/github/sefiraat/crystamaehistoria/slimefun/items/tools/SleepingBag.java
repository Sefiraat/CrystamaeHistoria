package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class SleepingBag extends UnplaceableBlock {

    @ParametersAreNonnullByDefault
    public SleepingBag(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            final Player player = e.getPlayer();
            final Location location = player.getLocation();
            final Block block = location.getBlock();

            if (location.getWorld().isDayTime()) {
                player.sendMessage(ThemeType.WARNING.getColor() + "You can only use a sleeping bag at night.");
                return;
            }
            if (location.getBlock().isEmpty() && GeneralUtils.hasPermission(player, location, Interaction.PLACE_BLOCK)) {
                final Location respawnLocation = player.getBedSpawnLocation();

                block.setType(Material.WHITE_BED);
                player.sleep(location, true);
                CrystamaeHistoria.getSpellMemory().getSleepingBags().put(player.getUniqueId(), location);
                player.sendMessage(ThemeType.SUCCESS.getColor() + "Respawn location reset to previous.");
                player.setBedSpawnLocation(respawnLocation, true);
            }

        };
    }
}
