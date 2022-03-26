package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class PrismaticGilder extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
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
            final Block block = e.getClickedBlock().orElse(null);
            if (block == null) {
                return;
            }

            final PrismaticGilderCache cache = PrismaticGilder.this.cacheMap.get(block.getLocation());
            final Player player = e.getPlayer();
            final ItemStack heldItem = player.getInventory().getItemInMainHand();
            if (cache != null && heldItem.getType() != Material.AIR) {
                cache.gildItem(block, heldItem, player);
            }
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
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);
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
