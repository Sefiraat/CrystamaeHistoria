package io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.StaveStorage;
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
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class StaveConfigurator extends TickingMenuBlock {

    private static final int[] BACKGROUND_SLOTS = {
        0, 2, 4, 5, 6, 7, 8, 9, 11, 13, 17, 18, 19, 20, 21, 22, 26, 27, 29, 31, 35, 36, 38, 40, 41, 42, 43, 44
    };
    private static final int[] BACKGROUND_INPUT = {
        14, 15, 16, 23, 25, 32, 33, 34
    };
    private static final int STAVE_SLOT = 24;
    private static final int LEFT_CLICK_SLOT = 10;
    private static final int RIGHT_CLICK_SLOT = 12;
    private static final int SHIFT_LEFT_CLICK_SLOT = 28;
    private static final int SHIFT_RIGHT_CLICK_SLOT = 30;
    private static final int LEFT_NOTE = 1;
    private static final int RIGHT_NOTE = 3;
    private static final int S_LEFT_NOTE = 37;
    private static final int S_RIGHT_NOTE = 39;
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
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND_INPUT, BACKGROUND_INPUT);

        blockMenuPreset.addItem(LEFT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(RIGHT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(S_LEFT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(S_RIGHT_NOTE, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
    }

    @Override
    protected void onNewInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
        super.onNewInstance(blockMenu, b);
        blockMenu.addMenuClickHandler(STAVE_SLOT, (player, i, itemStack, clickAction) -> {
            saveStave(blockMenu);
            clearPlates(blockMenu);
            return true;
        });
    }

    @Override
    protected void tick(@Nonnull Block block, BlockMenu blockMenu) {
        if (blockMenu.hasViewer()) {
            final ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
            final SlimefunItem sfStave = SlimefunItem.getByItem(stave);
            final boolean platesEmpty = platesEmpty(blockMenu);
            if (stave == null && !platesEmpty) {
                rejectItems(blockMenu);
            } else if (stave != null && sfStave instanceof Stave && platesEmpty) {
                final StaveStorage staveStorage = new StaveStorage(stave);
                final Map<SpellSlot, PlateStorage> map = staveStorage.getSpellInstanceMap();
                if (map != null) {
                    for (Map.Entry<SpellSlot, PlateStorage> entry : map.entrySet()) {
                        final SpellSlot spellSlot = entry.getKey();
                        final PlateStorage plateStorage = map.get(spellSlot);
                        final ItemStack plate = ChargedPlate.getChargedPlate(plateStorage);
                        blockMenu.replaceExistingItem(getSlot(spellSlot), plate);
                    }
                }
                staveStorage.getSpellInstanceMap().clear();
                ItemMeta itemMeta = stave.getItemMeta();
                DataTypeMethods.setCustom(
                    itemMeta,
                    Keys.PDC_STAVE_STORAGE,
                    PersistentStaveDataType.TYPE,
                    staveStorage.getSpellInstanceMap()
                );
                stave.setItemMeta(itemMeta);
            } else {
                rejectInvalid(blockMenu);
            }
        }
    }

    private void saveStave(@Nonnull BlockMenu blockMenu) {
        ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
        StaveStorage staveStorage = new StaveStorage();
        if (stave != null) {
            rejectInvalid(blockMenu);
            ItemMeta itemMeta = stave.getItemMeta();
            for (SpellSlot spellSlot : SpellSlot.getCashedValues()) {
                ItemStack plate = blockMenu.getItemInSlot(getSlot(spellSlot));
                if (plate != null && SlimefunItem.getByItem(plate) instanceof ChargedPlate) {
                    PlateStorage plateStorage = DataTypeMethods.getCustom(
                        plate.getItemMeta(),
                        Keys.PDC_PLATE_STORAGE,
                        PersistentPlateDataType.TYPE
                    );
                    staveStorage.setSlot(spellSlot, plateStorage);
                }
            }
            DataTypeMethods.setCustom(
                itemMeta,
                Keys.PDC_STAVE_STORAGE,
                PersistentStaveDataType.TYPE,
                staveStorage.getSpellInstanceMap()
            );
            stave.setItemMeta(itemMeta);
            StaveStorage.setStaveLore(stave, staveStorage);
        }
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent e, @Nonnull BlockMenu menu) {
        super.onBreak(e, menu);
        saveStave(menu);
        menu.dropItems(menu.getLocation(), STAVE_SLOT);
    }

    private boolean platesEmpty(@Nonnull BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            final ItemStack itemStack = blockMenu.getItemInSlot(slot);
            if (itemStack != null) {
                return false;
            }
        }
        return true;
    }

    public void rejectInvalid(BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            final ItemStack itemStack = blockMenu.getItemInSlot(slot);
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
            if (!(slimefunItem instanceof ChargedPlate)) {
                blockMenu.dropItems(blockMenu.getLocation(), slot);
            }
        }
    }

    private void clearPlates(@Nonnull BlockMenu blockMenu) {
        blockMenu.replaceExistingItem(LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(RIGHT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(SHIFT_LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(SHIFT_RIGHT_CLICK_SLOT, null);
    }

    private void rejectItems(@Nonnull BlockMenu blockMenu) {
        blockMenu.dropItems(
            blockMenu.getLocation(),
            LEFT_CLICK_SLOT,
            RIGHT_CLICK_SLOT,
            SHIFT_LEFT_CLICK_SLOT,
            SHIFT_RIGHT_CLICK_SLOT
        );
    }

    private int getSlot(@Nonnull SpellSlot spellSlot) {
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

    @Override
    protected boolean synchronous() {
        return true;
    }
}
