package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.slimefun.types.RefillableUseItem;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SpiritualSilken extends RefillableUseItem {

    private static final NamespacedKey KEY = Keys.newKey("uses");

    private static final Set<Material> VALID_MATERIALS = new HashSet<>();
    private final Map<Material, ItemSetting<Boolean>> settings = new EnumMap<>(Material.class);

    static {
        VALID_MATERIALS.add(Material.DIRT_PATH);
        VALID_MATERIALS.add(Material.INFESTED_COBBLESTONE);
        VALID_MATERIALS.add(Material.INFESTED_DEEPSLATE);
        VALID_MATERIALS.add(Material.INFESTED_STONE);
        VALID_MATERIALS.add(Material.INFESTED_CHISELED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_CRACKED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_MOSSY_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.LARGE_FERN);
        VALID_MATERIALS.add(Material.TALL_GRASS);
        VALID_MATERIALS.add(Material.BUDDING_AMETHYST);

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            VALID_MATERIALS.add(Material.REINFORCED_DEEPSLATE);
        }
    }

    @ParametersAreNonnullByDefault
    public SpiritualSilken(ItemGroup itemGroup,
                           SlimefunItemStack item,
                           RecipeType recipeType,
                           ItemStack[] recipe,
                           int amount
    ) {
        super(itemGroup, item, recipeType, recipe);
        for (Material material : VALID_MATERIALS) {
            final ItemSetting<Boolean> setting = new ItemSetting<>(this, material.name(), true);
            addItemSetting(setting);
            settings.put(material, setting);
        }
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Optional<Block> blockOptional = e.getClickedBlock();
            if (blockOptional.isPresent()) {
                final Block block = blockOptional.get();
                final Material material = block.getType();
                final ItemSetting<Boolean> setting = settings.get(material);
                if (VALID_MATERIALS.contains(material) && setting.getValue()) {
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
        return KEY;
    }
}
