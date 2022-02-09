package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.slimefun.types.RefillableUseItem;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

public class SpiritualSilken extends RefillableUseItem {

    private static final NamespacedKey key = Keys.newKey("uses");

    private static final Set<Material> validMaterials = EnumSet.of(
        Material.DIRT_PATH,
        Material.INFESTED_COBBLESTONE,
        Material.INFESTED_DEEPSLATE,
        Material.INFESTED_STONE,
        Material.INFESTED_CHISELED_STONE_BRICKS,
        Material.INFESTED_CRACKED_STONE_BRICKS,
        Material.INFESTED_MOSSY_STONE_BRICKS,
        Material.INFESTED_STONE_BRICKS,
        Material.LARGE_FERN,
        Material.TALL_GRASS,
        Material.BUDDING_AMETHYST
    );

    @ParametersAreNonnullByDefault
    public SpiritualSilken(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(itemGroup, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Optional<Block> blockOptional = e.getClickedBlock();
            if (blockOptional.isPresent()) {
                Block block = blockOptional.get();
                Material material = block.getType();
                if (validMaterials.contains(material)) {
                    block.setType(Material.AIR);
                    block.getState().update(true, true);
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(material));
                    damageItem(e.getPlayer(), e.getItem());
                } else {
                    e.getPlayer().sendMessage(ThemeType.WARNING.getColor() + "This block doesn't seem to react.");
                }
            }
        };
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }
}
