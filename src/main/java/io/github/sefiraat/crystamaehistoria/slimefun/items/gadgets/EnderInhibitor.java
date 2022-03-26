package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
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
import org.bukkit.entity.Enderman;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EnderInhibitor extends SlimefunItem {

    @Getter
    private final double radius;

    @ParametersAreNonnullByDefault
    public EnderInhibitor(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double radius) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
    }

    @Override
    public void preRegister() {
        addItemHandler(onBlockPlace(), onBlockBreak(), onTick());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            @ParametersAreNonnullByDefault
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo(event.getBlock(), "CH_UUID", event.getPlayer().getUniqueId().toString());
            }
        };
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            @ParametersAreNonnullByDefault
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
            }
        };
    }

    private BlockTicker onTick() {
        return new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                for (Enderman enderman : block.getWorld().getNearbyEntitiesByType(Enderman.class, block.getLocation(), radius)) {
                    CrystamaeHistoria.getSpellMemory().getInhibitedEndermen().put(
                        enderman.getUniqueId(),
                        System.currentTimeMillis() + 2000
                    );
                }
            }
        };
    }
}
