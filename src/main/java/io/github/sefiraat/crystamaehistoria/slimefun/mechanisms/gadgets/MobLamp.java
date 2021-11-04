package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Monster;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;

public class MobLamp extends SlimefunItem {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };

    @Getter
    private final double radius;
    @Getter
    private final double force;

    @ParametersAreNonnullByDefault
    public MobLamp(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double radius, double force) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
        this.force = force;
        this.addItemHandler(
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent event) {
                    BlockStorage.addBlockInfo(event.getBlock(), "CH_UUID", event.getPlayer().getUniqueId().toString());
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                    BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    for (Monster monster : block.getWorld().getNearbyEntitiesByType(Monster.class, block.getLocation(), radius)) {
                        UUID uuid = UUID.fromString(BlockStorage.getLocationInfo(block.getLocation(), "CH_UUID"));
                        GeneralUtils.pushEntity(
                            uuid,
                            block.getLocation().clone().add(0.5, 0.5, 0.5),
                            monster,
                            force
                        );
                    }
                }
            }
        );
    }
}
