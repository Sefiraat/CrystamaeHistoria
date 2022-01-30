package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.staveconfigurator;

import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstanceStave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class StaveConfigurator extends MenuBlock {

    private static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 6, 8, 9, 11, 13, 15, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 29, 31, 33, 35, 36, 37, 38, 39, 40, 42, 44
    };
    private static final int[] BACKGROUND_INPUT = {
        10, 28
    };
    private static final int STAVE_SLOT = 19;
    private static final int REMOVE_PLATES = 12;
    private static final int ADD_PLATES = 30;
    private static final int LEFT_CLICK_SLOT = 14;
    private static final int RIGHT_CLICK_SLOT = 16;
    private static final int SHIFT_LEFT_CLICK_SLOT = 32;
    private static final int SHIFT_RIGHT_CLICK_SLOT = 34;
    private static final int LEFT_NOTE = 5;
    private static final int RIGHT_NOTE = 7;
    private static final int S_LEFT_NOTE = 41;
    private static final int S_RIGHT_NOTE = 43;
    private static final int[] PLATE_SLOTS = {
        LEFT_CLICK_SLOT,
        RIGHT_CLICK_SLOT,
        SHIFT_LEFT_CLICK_SLOT,
        SHIFT_RIGHT_CLICK_SLOT
    };

    @ParametersAreNonnullByDefault
    public StaveConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND, BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GuiElements.MENU_STAVE_INPUT, BACKGROUND_INPUT);

        blockMenuPreset.addItem(LEFT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(RIGHT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(S_LEFT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(S_RIGHT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);

        blockMenuPreset.addItem(REMOVE_PLATES, GuiElements.MENU_REMOVE_PLATES, (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(ADD_PLATES, GuiElements.MENU_SAVE_STAVE, (player, i, itemStack, clickAction) -> false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);

        blockMenu.addMenuClickHandler(REMOVE_PLATES, (player, i, itemStack, clickAction) -> {
            rejectItems(blockMenu);
            final ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
            final SlimefunItem sfStave = SlimefunItem.getByItem(stave);

            if (stave == null || !(sfStave instanceof Stave)) {
                return false;
            }

            final InstanceStave staveInstance = new InstanceStave(stave);
            final Map<SpellSlot, InstancePlate> map = staveInstance.getSpellInstanceMap();
            if (map != null) {
                for (Map.Entry<SpellSlot, InstancePlate> entry : map.entrySet()) {
                    final SpellSlot spellSlot = entry.getKey();
                    final InstancePlate instancePlate = map.get(spellSlot);
                    final ItemStack plate = ChargedPlate.getChargedPlate(instancePlate);
                    blockMenu.replaceExistingItem(getSlot(spellSlot), plate);
                }
            }
            staveInstance.getSpellInstanceMap().clear();
            ItemMeta itemMeta = stave.getItemMeta();
            DataTypeMethods.setCustom(
                itemMeta,
                Keys.PDC_STAVE_STORAGE,
                PersistentStaveDataType.TYPE,
                staveInstance.getSpellInstanceMap()
            );
            stave.setItemMeta(itemMeta);
            staveInstance.buildLore();
            return false;
        });

        blockMenu.addMenuClickHandler(ADD_PLATES, (player, i, itemStack, clickAction) -> {
            final ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
            final SlimefunItem sfStave = SlimefunItem.getByItem(stave);
            if (stave != null
                && sfStave instanceof Stave
                && !platesEmpty(blockMenu)
                && staveIsEmpty(stave)
            ) {
                final InstanceStave staveInstance = new InstanceStave(stave);
                final ItemMeta staveMeta = stave.getItemMeta();
                rejectInvalid(blockMenu);
                for (SpellSlot spellSlot : SpellSlot.getCashedValues()) {
                    final ItemStack plate = blockMenu.getItemInSlot(getSlot(spellSlot));
                    if (plate != null && SlimefunItem.getByItem(plate) instanceof ChargedPlate) {
                        final InstancePlate instancePlate = DataTypeMethods.getCustom(
                            plate.getItemMeta(),
                            Keys.PDC_PLATE_STORAGE,
                            PersistentPlateDataType.TYPE
                        );
                        staveInstance.setSlot(spellSlot, instancePlate);
                    }
                }
                DataTypeMethods.setCustom(
                    staveMeta,
                    Keys.PDC_STAVE_STORAGE,
                    PersistentStaveDataType.TYPE,
                    staveInstance.getSpellInstanceMap()
                );
                stave.setItemMeta(staveMeta);
                staveInstance.buildLore();
                clearPlates(blockMenu);
            }
            return false;
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        super.onBreak(e, menu);
        super.onBreak(e, menu);
        Location location = menu.getLocation();
        menu.dropItems(location, LEFT_CLICK_SLOT);
        menu.dropItems(location, RIGHT_CLICK_SLOT);
        menu.dropItems(location, SHIFT_LEFT_CLICK_SLOT);
        menu.dropItems(location, SHIFT_RIGHT_CLICK_SLOT);
        menu.dropItems(location, STAVE_SLOT);
    }

    @ParametersAreNonnullByDefault
    public void rejectInvalid(BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            final ItemStack itemStack = blockMenu.getItemInSlot(slot);
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
            if (!(slimefunItem instanceof ChargedPlate)) {
                blockMenu.dropItems(blockMenu.getLocation(), slot);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void clearPlates(BlockMenu blockMenu) {
        blockMenu.replaceExistingItem(LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(RIGHT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(SHIFT_LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(SHIFT_RIGHT_CLICK_SLOT, null);
    }

    @ParametersAreNonnullByDefault
    private void rejectItems(BlockMenu blockMenu) {
        blockMenu.dropItems(
            blockMenu.getLocation(),
            LEFT_CLICK_SLOT,
            RIGHT_CLICK_SLOT,
            SHIFT_LEFT_CLICK_SLOT,
            SHIFT_RIGHT_CLICK_SLOT
        );
    }

    @ParametersAreNonnullByDefault
    private boolean platesEmpty(BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            final ItemStack itemStack = blockMenu.getItemInSlot(slot);
            if (itemStack != null) {
                return false;
            }
        }
        return true;
    }

    @ParametersAreNonnullByDefault
    private boolean staveIsEmpty(ItemStack stave) {
        final InstanceStave instanceStave = new InstanceStave(stave);
        return instanceStave.getSpellInstanceMap().size() == 0;
    }

    @ParametersAreNonnullByDefault
    private int getSlot(SpellSlot spellSlot) {
        switch (spellSlot) {
            case LEFT_CLICK:
                return LEFT_CLICK_SLOT;
            case RIGHT_CLICK:
                return RIGHT_CLICK_SLOT;
            case SHIFT_LEFT_CLICK:
                return SHIFT_LEFT_CLICK_SLOT;
            case SHIFT_RIGHT_CLICK:
                return SHIFT_RIGHT_CLICK_SLOT;
            default:
                throw new IllegalStateException("Unexpected value: " + spellSlot);
        }
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
