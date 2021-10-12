package io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator;

import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.sefiraat.crystamaehistoria.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class StaveConfigurator extends MenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 2, 3, 5, 9, 11, 12, 14, 18, 19, 20, 21, 22, 23, 27, 28, 29, 30, 31, 32, 36, 38, 39, 41, 45, 47, 48, 50
    };
    protected static final int[] BACKGROUND_INPUT = {
        12, 13, 14, 21, 23, 30, 31, 32
    };
    protected static final int[] BACKGROUND_OUTPUT = {
        33, 34, 35, 42, 44, 51, 52, 53
    };
    protected static final int STAVE_SLOT = 16;
    protected static final int OUTPUT_SLOT = 16;
    protected static final int PLATE_1 = 10;
    protected static final int PLATE_2 = 13;
    protected static final int PLATE_3 = 37;
    protected static final int PLATE_4 = 40;
    protected static final int NOTE_1 = 1;
    protected static final int NOTE_2 = 4;
    protected static final int NOTE_3 = 46;
    protected static final int NOTE_4 = 49;

    @ParametersAreNonnullByDefault
    public StaveConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND, BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND_INPUT, BACKGROUND_INPUT);
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND_OUTPUT, BACKGROUND_OUTPUT);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

}
