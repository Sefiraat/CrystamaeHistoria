package io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.StaveStorage;
import io.github.sefiraat.crystamaehistoria.theme.GuiElements;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.StackUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.NonNull;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class StaveConfigurator extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 2, 4, 5, 6, 7, 8, 9, 11, 13, 17, 18, 19, 20, 21, 22, 26, 27, 29, 31, 35, 36, 38, 40, 41, 42, 43, 44
    };
    protected static final int[] BACKGROUND_INPUT = {
        14, 15, 16, 23, 25, 32, 33, 34
    };
    protected static final int STAVE_SLOT = 24;
    protected static final int LEFT_CLICK_SLOT = 10;
    protected static final int RIGHT_CLICK_SLOT = 12;
    protected static final int S_LEFT_CLICK_SLOT = 28;
    protected static final int S_RIGHT_CLICK_SLOT = 30;
    protected static final int LEFT_NOTE = 1;
    protected static final int RIGHT_NOTE = 3;
    protected static final int S_LEFT_NOTE = 37;
    protected static final int S_RIGHT_NOTE = 39;

    @ParametersAreNonnullByDefault
    public StaveConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void tick(@Nonnull Block block, BlockMenu blockMenu) {
        if (blockMenu.hasViewer()) {
            final ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
            final SlimefunItem sfStave = SlimefunItem.getByItem(stave);
            final boolean platesEmpty = platesEmpty(blockMenu);
            if (stave == null && !platesEmpty) {
                clearPlates(blockMenu);
            } else if (stave != null && sfStave instanceof Stave && platesEmpty) {
                final StaveStorage staveStorage = new StaveStorage(stave);
                final Map<SpellSlot, PlateStorage> map = staveStorage.getSpellInstanceMap();
                if (map != null) {
                    for (Map.Entry<SpellSlot, PlateStorage> entry : map.entrySet()) {
                        final SpellSlot spellSlot = entry.getKey();
                        final PlateStorage plateStorage = map.get(spellSlot);
                        final ItemStack plate = ChargedPlate.getChargedPlate(plateStorage);
                        blockMenu.replaceExistingItem(getPlateSlot(spellSlot), plate);
                    }
                }
                staveStorage.getSpellInstanceMap().clear();
                ItemMeta itemMeta = stave.getItemMeta();
                StoryUtils.setCustom(
                    itemMeta,
                    CrystamaeHistoria.getKeys().getPdcStaveStorage(),
                    PersistentStaveDataType.TYPE,
                    staveStorage.getSpellInstanceMap()
                );
                stave.setItemMeta(itemMeta);
            }
        }
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

    private int getPlateSlot(@Nonnull SpellSlot spellSlot) {
        switch (spellSlot) {
            case LEFT_CLICK:
                return LEFT_CLICK_SLOT;
            case RIGHT_CLICK:
                return RIGHT_CLICK_SLOT;
            case SHIFT_LEFT_CLICK:
                return S_LEFT_CLICK_SLOT;
            case SHIFT_RIGHT_CLICK:
                return S_RIGHT_CLICK_SLOT;
            default:
                throw new IllegalStateException("Unexpected value: " + spellSlot);
        }
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
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    private boolean platesEmpty(@Nonnull BlockMenu blockMenu) {
        ItemStack p1 = blockMenu.getItemInSlot(LEFT_CLICK_SLOT);
        ItemStack p2 = blockMenu.getItemInSlot(RIGHT_CLICK_SLOT);
        ItemStack p3 = blockMenu.getItemInSlot(S_LEFT_CLICK_SLOT);
        ItemStack p4 = blockMenu.getItemInSlot(S_RIGHT_CLICK_SLOT);
        return p1 == null
            && p2 == null
            && p3 == null
            && p4 == null;
    }

    private void saveStave(@Nonnull BlockMenu blockMenu) {
        Keys keys = CrystamaeHistoria.getKeys();
        ItemStack stave = blockMenu.getItemInSlot(STAVE_SLOT);
        StaveStorage staveStorage = new StaveStorage();
        if (stave != null) {
            ItemMeta itemMeta = stave.getItemMeta();
            for (SpellSlot spellSlot : SpellSlot.getCashedValues()) {
                ItemStack plate = blockMenu.getItemInSlot(getPlateSlot(spellSlot));
                if (plate != null && SlimefunItem.getByItem(plate) instanceof ChargedPlate) {
                    PlateStorage plateStorage = StoryUtils.getCustom(
                        plate.getItemMeta(),
                        keys.getPdcPlateStorage(),
                        PersistentPlateDataType.TYPE
                    );
                    staveStorage.setSlot(spellSlot, plateStorage);
                }
            }
            StoryUtils.setCustom(
                itemMeta,
                keys.getPdcStaveStorage(),
                PersistentStaveDataType.TYPE,
                staveStorage.getSpellInstanceMap()
            );
            stave.setItemMeta(itemMeta);
        }
    }

    private void clearPlates(@Nonnull BlockMenu blockMenu) {
        blockMenu.replaceExistingItem(LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(RIGHT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(S_LEFT_CLICK_SLOT, null);
        blockMenu.replaceExistingItem(S_RIGHT_CLICK_SLOT, null);
    }
}
