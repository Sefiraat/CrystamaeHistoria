package io.github.sefiraat.crystamaehistoria.slimefun.machines.imbuer;

import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.sefiraat.crystamaehistoria.theme.GUIElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class StaveConfigurator extends MenuBlock {

    protected static final int[] BACKGROUND_SPELLS = {
            0, 1, 2, 3, 4, 5, 9, 11, 12, 14, 18, 19, 20, 21, 22, 23, 27, 28, 29, 30, 31, 32, 36, 38, 39, 41, 45, 46, 47, 48, 49, 50
    };
    protected static final int[] BACKGROUND_INPUT = {
            12, 13, 14, 21, 23, 30, 31, 32
    };
    protected static final int[] BACKGROUND_PROCESS = {
            12, 13, 14, 21, 23, 30, 31, 32
    };
    protected static final int SPELL_SLOT_1 = 10;
    protected static final int SPELL_SLOT_2 = 13;
    protected static final int SPELL_SLOT_3 = 37;
    protected static final int SPELL_SLOT_4 = 40;
    protected static final int PROCESS_BUTTON = 43;
    protected static final int STAVE_INPUT = 16;

    @ParametersAreNonnullByDefault
    public StaveConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GUIElements.menuBackground(), BACKGROUND_PROCESS);
        blockMenuPreset.drawBackground(GUIElements.menuBackgroundInput(), BACKGROUND_INPUT);
        blockMenuPreset.drawBackground(GUIElements.menuBackgroundInput(), BACKGROUND_SPELLS);
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
        blockMenu.dropItems(location, STAVE_INPUT);
    }

}
