package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.utils.GildingUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class PrismaticGilder extends TickingMenuBlock {

    @Getter
    protected final Map<Location, PrismaticGilderCache> cacheMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public PrismaticGilder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            onBlockPlace(),
            onBlockUse()
        );
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                final Location location = event.getBlockPlaced().getLocation();
                final PrismaticGilderCache cache = new PrismaticGilderCache(BlockStorage.getInventory(location), 5);

                cache.setActivePlayer(event.getPlayer());
                cacheMap.put(location, cache);
            }
        };
    }

    private BlockUseHandler onBlockUse() {
        return e -> {

        };
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        final PrismaticGilderCache cache = PrismaticGilder.this.cacheMap.get(block.getLocation());

        if (cache != null) {
            cache.consumeItems();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.addMenuOpeningHandler(HumanEntity::closeInventory);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent event, BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);

        final Location location = blockMenu.getLocation();
        final PrismaticGilderCache prismaticGilderCache = cacheMap.remove(location);

        boolean punish = false;

        if (prismaticGilderCache != null) {
            prismaticGilderCache.kill(location);
            punish = prismaticGilderCache.getFillAmount() > 0;
        }

        if (punish) {
            blockMenu.getLocation().getWorld().createExplosion(
                event.getBlock().getLocation(),
                2,
                true,
                false
            );
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!cacheMap.containsKey(blockMenu.getLocation())) {
            PrismaticGilderCache cache = new PrismaticGilderCache(blockMenu, 5);
            String s = BlockStorage.getLocationInfo(blockMenu.getLocation(), PrismaticGilderCache.AMOUNT);
            if (s != null) {
                cache.setFillAmount(Integer.parseInt(s));
            }
            cacheMap.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

}
