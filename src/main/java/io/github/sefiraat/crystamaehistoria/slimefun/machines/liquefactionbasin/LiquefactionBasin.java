package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel.ChroniclerPanelCache;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class LiquefactionBasin extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    protected static final int[] BACKGROUND_INPUT = {
        12, 13, 14, 21, 23, 30, 31, 32
    };
    protected static final int INPUT_SLOT = 22;
    protected static final Map<Location, LiquefactionBasinCache> CACHE_MAP = new HashMap<>();

    @ParametersAreNonnullByDefault
    public LiquefactionBasin(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent event) {
                final Location location = event.getBlockPlaced().getLocation();
                final LiquefactionBasinCache cache = new LiquefactionBasinCache(BlockStorage.getInventory(location));
                cache.setActivePlayer(event.getPlayer());
                CACHE_MAP.put(location, cache);
            }
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        LiquefactionBasinCache cache = LiquefactionBasin.CACHE_MAP.get(block.getLocation());
        if (cache != null) {
            cache.consumeItems();
            cache.syncBlock();
            // TODO Remove
            if (cache.getActivePlayer() != null) {
                CrystamaeHistoria.log(Level.WARNING, "LB : " + cache.getActivePlayer().toString());
            } else {
                CrystamaeHistoria.log(Level.WARNING, "LB : Empty");
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        // TODO Replace handler with method closing inv and displaying contents (if holding item)
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
        Location location = blockMenu.getLocation();
        LiquefactionBasinCache liquefactionBasinCache = CACHE_MAP.remove(location);
        if (liquefactionBasinCache != null) {
            liquefactionBasinCache.kill(location);
        }
        blockMenu.dropItems(location, INPUT_SLOT);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!CACHE_MAP.containsKey(blockMenu.getLocation())) {
            LiquefactionBasinCache cache = new LiquefactionBasinCache(blockMenu);
            Config c = BlockStorage.getLocationInfo(blockMenu.getLocation());

            for (String key : c.getKeys()) {
                if (key.startsWith(LiquefactionBasinCache.CH_LEVEL_PREFIX)) {
                    String id = key.replace(LiquefactionBasinCache.CH_LEVEL_PREFIX, "");
                    int amount = Integer.parseInt(c.getString(key));
                    cache.getContentMap().put(StoryType.valueOf(id), amount);
                }
            }

            CACHE_MAP.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

}
