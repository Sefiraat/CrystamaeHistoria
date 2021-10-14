package io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator;

import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class StaveConfigurator extends MenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 2, 3, 5, 9, 11, 12, 14, 18, 19, 20, 21, 22, 23, 27, 28, 29, 30, 31, 32, 36, 38, 39, 41, 45, 47, 48, 50
    };
    protected static final int[] BACKGROUND_INPUT = {
        6, 7, 8, 15, 17, 24, 25, 26
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

        blockMenuPreset.addItem(NOTE_1, GuiElements.getSpellSlotPane(SpellSlot.LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(NOTE_2, GuiElements.getSpellSlotPane(SpellSlot.RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(NOTE_3, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(NOTE_4, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);

        blockMenuPreset.addMenuClickHandler(STAVE_SLOT, (player, i, itemStack, clickAction) -> {
            final ItemStack cursor = player.getItemOnCursor();
            final SlimefunItem stave = SlimefunItem.getByItem(cursor);
            if (cursor.getType() == Material.AIR && stave instanceof Stave) {
                // Cursor is Air and a Stave is in the slot - save, remove stave and clear slots.
                clearPlates(blockMenuPreset);
                return false;
            }
            return false;
        });

        blockMenuPreset.addMenuClickHandler(PLATE_1, (player, i, itemStack, clickAction) -> configureSlot(player, itemStack, SpellSlot.LEFT_CLICK, blockMenuPreset.getItemInSlot(STAVE_SLOT)));
        blockMenuPreset.addMenuClickHandler(PLATE_2, (player, i, itemStack, clickAction) -> configureSlot(player, itemStack, SpellSlot.RIGHT_CLICK, blockMenuPreset.getItemInSlot(STAVE_SLOT)));
        blockMenuPreset.addMenuClickHandler(PLATE_3, (player, i, itemStack, clickAction) -> configureSlot(player, itemStack, SpellSlot.SHIFT_LEFT_CLICK, blockMenuPreset.getItemInSlot(STAVE_SLOT)));
        blockMenuPreset.addMenuClickHandler(PLATE_4, (player, i, itemStack, clickAction) -> configureSlot(player, itemStack, SpellSlot.SHIFT_RIGHT_CLICK, blockMenuPreset.getItemInSlot(STAVE_SLOT)));

        blockMenuPreset.addPlayerInventoryClickHandler((player, i, clickedItem, clickAction) -> {
            SlimefunItem clickedSfItem = SlimefunItem.getByItem(clickedItem);
            ItemStack itemStack = blockMenuPreset.getItemInSlot(STAVE_SLOT);
            if (clickAction.isShiftClicked() && clickedSfItem instanceof Stave && itemStack == null) {

            }
            return false;
        });

    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }


    private void saveStave(BlockMenuPreset blockMenu) {

    }

    private void clearPlates(BlockMenuPreset blockMenu) {
        blockMenu.addItem(PLATE_1, null, (player, i, itemStack, clickAction) -> false);
        blockMenu.addItem(PLATE_2, null, (player, i, itemStack, clickAction) -> false);
        blockMenu.addItem(PLATE_3, null, (player, i, itemStack, clickAction) -> false);
        blockMenu.addItem(PLATE_4, null, (player, i, itemStack, clickAction) -> false);
    }

    @ParametersAreNonnullByDefault
    private boolean configureSlot(Player player, ItemStack itemStack, SpellSlot spellSlot, ItemStack stave) {
        final ItemStack cursor = player.getItemOnCursor();
        final SlimefunItem plate = SlimefunItem.getByItem(cursor);
        final SlimefunItem sfStave = SlimefunItem.getByItem(stave);
        if (canSwap(plate, cursor) && sfStave instanceof Stave) {
            // Can swap out
            return true;
        }
        return false;
    }

    private boolean canSwap(SlimefunItem slimefunItem, ItemStack cursor) {
        return (slimefunItem instanceof ChargedPlate || cursor.getType() == Material.AIR);
    }

}
