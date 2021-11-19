package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class RealisationAltar extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    protected static final int[] BACKGROUND_INPUT = {
        12, 13, 14, 21, 23, 30, 31, 32
    };
    protected static final int INPUT_SLOT = 22;

    protected static final Map<Location, RealisationAltarCache> CACHES = new HashMap<>();

    @Getter
    private final int tier;

    @ParametersAreNonnullByDefault
    public RealisationAltar(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int tier) {
        super(itemGroup, item, recipeType, recipe);
        this.tier = tier;
        this.addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                final Location location = event.getBlockPlaced().getLocation();
                final RealisationAltarCache cache = new RealisationAltarCache(BlockStorage.getInventory(location), tier);
                cache.setActivePlayer(event.getPlayer());
                CACHES.put(location, cache);
            }
        });
    }

    public static Map<Location, RealisationAltarCache> getCaches() {
        return CACHES;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        RealisationAltarCache cache = RealisationAltar.CACHES.get(block.getLocation());
        if (cache != null) {
            cache.process();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND, BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND_INPUT, BACKGROUND_INPUT);
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
        RealisationAltarCache realisationAltarCache = CACHES.remove(location);
        if (realisationAltarCache != null) {
            realisationAltarCache.kill();
        }
        blockMenu.dropItems(location, INPUT_SLOT);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!CACHES.containsKey(blockMenu.getLocation())) {
            RealisationAltarCache cache = new RealisationAltarCache(blockMenu, this.tier);
            cache.loadMap();
            CACHES.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }
}
