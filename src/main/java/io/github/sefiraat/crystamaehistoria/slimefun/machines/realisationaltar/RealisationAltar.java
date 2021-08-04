package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.mooy1.infinitylib.slimefun.AbstractTickingContainer;
import io.github.sefiraat.crystamaehistoria.theme.GUIElements;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class RealisationAltar extends AbstractTickingContainer {

    protected static final int[] BACKGROUND_SLOTS = {
            0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,
            9 ,10,11,         15,16,17,
            18,19,20,         24,25,26,
            27,28,29,         33,34,35,
            36,37,38,39,40,41,42,43,44
    };
    protected static final int[] BACKGROUND_INPUT = {
            12,13,14,
            21,   23,
            30,31,32
    };
    protected static final int INPUT_SLOT = 22;

    private final Map<Location, RealisationAltarCache> caches = new HashMap<>();

    public RealisationAltar(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void tick(@NotNull BlockMenu blockMenu, @NotNull Block block) {
        RealisationAltarCache cache = RealisationAltar.this.caches.get(block.getLocation());
        if (cache != null) {
            cache.process();
        }
    }

    @Override
    protected void setupMenu(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GUIElements.menuBackground(), BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GUIElements.menuBackgroundInput(), BACKGROUND_INPUT);
    }

    @Override
    protected int @NotNull [] getTransportSlots(@NotNull DirtyChestMenu dirtyChestMenu, @NotNull ItemTransportFlow itemTransportFlow, ItemStack itemStack) {
        return new int[0];
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent event, @Nonnull BlockMenu blockMenu, @Nonnull Location location) {
        super.onBreak(event, blockMenu, location);
        RealisationAltarCache realisationAltarCache = caches.remove(location);
        if (realisationAltarCache != null) {
            realisationAltarCache.kill(location);
        }
        blockMenu.dropItems(location, INPUT_SLOT);
    }

    @Override
    protected void onNewInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
        super.onNewInstance(blockMenu, b);
        RealisationAltarCache cache = new RealisationAltarCache(blockMenu);
        caches.put(blockMenu.getLocation(), cache);
    }

    @Override
    protected boolean synchronised() {
        return true;
    }
}
