package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.theme.GUIElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class RealisationAltar extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 15, 16, 17,
            18, 19, 20, 24, 25, 26,
            27, 28, 29, 33, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    protected static final int[] BACKGROUND_INPUT = {
            12, 13, 14,
            21, 23,
            30, 31, 32
    };
    protected static final int INPUT_SLOT = 22;

    private final Map<Location, RealisationAltarCache> caches = new HashMap<>();

    @ParametersAreNonnullByDefault
    public RealisationAltar(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        RealisationAltarCache cache = RealisationAltar.this.caches.get(block.getLocation());
        if (cache != null) {
            cache.process();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GUIElements.menuBackground(), BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GUIElements.menuBackgroundInput(), BACKGROUND_INPUT);
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
        RealisationAltarCache realisationAltarCache = caches.remove(location);
        if (realisationAltarCache != null) {
            realisationAltarCache.kill();
        }
        blockMenu.dropItems(location, INPUT_SLOT);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        RealisationAltarCache cache = new RealisationAltarCache(blockMenu);
        cache.loadMap();
        caches.put(blockMenu.getLocation(), cache);
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

}
