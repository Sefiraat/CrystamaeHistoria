package io.github.sefiraat.crystamaehistoria.slimefun.tools.satchel;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentSatchelInstanceType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrystamageSatchel extends UnplaceableBlock {

    private final int tier;

    @ParametersAreNonnullByDefault
    public CrystamageSatchel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int tier) {
        super(itemGroup, item, recipeType, recipe);
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public void preRegister() {
        addItemHandler(onOpen());
    }

    public ItemUseHandler onOpen() {
        return event -> {
            event.cancel();
            SatchelInstance satchelInstance = DataTypeMethods.getCustom(
                event.getItem().getItemMeta(),
                Keys.PDC_SATCHEL_STORAGE,
                PersistentSatchelInstanceType.TYPE
            );

            if (satchelInstance == null) {
                event.getPlayer().sendMessage(
                    MessageFormat.format(
                        "{0}Satchel is new, setting up",
                        ThemeType.WARNING.getColor()
                    )
                );
                satchelInstance = generateNewSatchelInstance(event.getItem(), this.tier);
            }
            final SatchelGui satchelGui = new SatchelGui(satchelInstance, event.getItem());
            satchelGui.open(event.getPlayer());
        };
    }

    public boolean tryAddItem(@Nonnull ItemStack satchel, @Nonnull ItemStack crystalStack, @Nonnull Crystal crystal) {
        return canHold(crystal) && tryAddItem(satchel, crystal.getRarity(), crystal.getType(), crystalStack.getAmount());
    }

    public boolean tryAddItem(@Nonnull ItemStack satchel, @Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        final ItemMeta itemMeta = satchel.getItemMeta();
        final SatchelInstance satchelInstance = DataTypeMethods.getCustom(
            itemMeta,
            Keys.PDC_SATCHEL_STORAGE,
            PersistentSatchelInstanceType.TYPE
        );

        satchelInstance.addAmount(rarity, type, amount);
        DataTypeMethods.setCustom(
            itemMeta,
            Keys.PDC_SATCHEL_STORAGE,
            PersistentSatchelInstanceType.TYPE,
            satchelInstance
        );
        satchel.setItemMeta(itemMeta);
        return true;
    }

    private boolean canHold(@Nonnull Crystal crystal) {
        switch (crystal.getRarity()) {
            case UNIQUE:
                return true;
            case COMMON:
                return this.tier >= 2;
            case UNCOMMON:
                return this.tier >= 3;
            case RARE:
                return this.tier >= 4;
            case EPIC:
                return this.tier >= 5;
            case MYTHICAL:
                return this.tier >= 6;
        }
        return false;
    }

    private SatchelInstance generateNewSatchelInstance(@Nonnull ItemStack itemStack, int tier) {
        SatchelInstance satchelInstance = new SatchelInstance(System.currentTimeMillis(), tier);
        ItemMeta itemMeta = itemStack.getItemMeta();
        DataTypeMethods.setCustom(itemMeta, Keys.PDC_SATCHEL_STORAGE, PersistentSatchelInstanceType.TYPE, satchelInstance);
        itemStack.setItemMeta(itemMeta);
        return satchelInstance;
    }

    private static final class SatchelGui extends ChestMenu {

        private static final int[] SLOTS_UNIQUE = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8
        };

        private static final int[] SLOTS_COMMON = new int[]{
            9, 10, 11, 12, 13, 14, 15, 16, 17
        };

        private static final int[] SLOTS_UNCOMMON = new int[]{
            18, 19, 20, 21, 22, 23, 24, 25, 26
        };

        private static final int[] SLOTS_RARE = new int[]{
            27, 28, 29, 30, 31, 32, 33, 34, 35
        };

        private static final int[] SLOTS_EPIC = new int[]{
            36, 37, 38, 39, 40, 41, 42, 43, 44
        };

        private static final int[] SLOTS_MYTHICAL = new int[]{
            45, 46, 47, 48, 49, 50, 51, 52, 53
        };

        @Nonnull
        private final SatchelInstance instance;
        @Nonnull
        private final ItemStack itemStack;
        @Nonnull
        private final CrystamageSatchel satchel;

        private SatchelGui(@Nonnull SatchelInstance instance, @Nonnull ItemStack itemStack) {
            super("Crystamage Satchel - Tier " + instance.getTier());
            this.instance = instance;
            this.itemStack = itemStack;
            this.satchel = (CrystamageSatchel) Objects.requireNonNull(SlimefunItem.getByItem(itemStack));

            for (int i = 0; i < 54; i++) {
                addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
            }

            setupAllItems();
            addPlayerInventoryClickHandler(ChestMenuUtils.getEmptyClickHandler());
        }

        private void setupAllItems() {
            if (this.satchel.tier >= 1) {
                addGuiItems(SLOTS_UNIQUE, StoryRarity.UNIQUE);
            }

            if (this.satchel.tier >= 2) {
                addGuiItems(SLOTS_COMMON, StoryRarity.COMMON);
            }

            if (this.satchel.tier >= 3) {
                addGuiItems(SLOTS_UNCOMMON, StoryRarity.UNCOMMON);
            }

            if (this.satchel.tier >= 4) {
                addGuiItems(SLOTS_RARE, StoryRarity.RARE);
            }

            if (this.satchel.tier >= 5) {
                addGuiItems(SLOTS_EPIC, StoryRarity.EPIC);
            }

            if (this.satchel.tier >= 6) {
                addGuiItems(SLOTS_MYTHICAL, StoryRarity.MYTHICAL);
            }
        }

        private void addGuiItems(int[] slots, @Nonnull StoryRarity rarity) {
            int i = 1;
            for (int slot : slots) {
                final StoryType type = StoryType.getById(i);
                final ItemStack loreStack = getStackWithAmount(
                    Materials.getCrystalMap().get(rarity).get(type).getItem().clone(),
                    this.instance.getAmount(rarity, type)
                );
                replaceExistingItem(slot, loreStack);
                addMenuClickHandler(slot, (p, slot1, item, action) -> {
                    tryWithdraw(p, rarity, type, action.isShiftClicked());
                    return false;
                });
                i++;
            }
        }

        private void tryWithdraw(@Nonnull Player player, @Nonnull StoryRarity rarity, @Nonnull StoryType type, boolean stack) {
            int firstEmpty = player.getInventory().firstEmpty();
            if (firstEmpty != -1) {
                final int stored = this.instance.getAmount(rarity, type);

                if (stored == 0) {
                    return;
                }

                final ItemStack itemToWithdraw = Materials.getCrystalMap().get(rarity).get(type).getItem().clone();
                final int amountToWithdraw = stack ? Math.min(stored, itemToWithdraw.getMaxStackSize()) : 1;

                itemToWithdraw.setAmount(amountToWithdraw);
                instance.removeAmount(rarity, type, amountToWithdraw);
                player.getInventory().addItem(itemToWithdraw);
                saveInstance();
                setupAllItems();
            }
        }

        @Override
        public void open(Player... players) {
            this.instance.setLastUser(players[0].getDisplayName());
            saveInstance();
            super.open(players);
        }

        private void saveInstance() {
            ItemMeta itemMeta = this.itemStack.getItemMeta();
            DataTypeMethods.setCustom(
                itemMeta,
                Keys.PDC_SATCHEL_STORAGE,
                PersistentSatchelInstanceType.TYPE,
                instance
            );
            this.itemStack.setItemMeta(itemMeta);
        }


        public SatchelInstance getInstance() {
            return this.instance;
        }

        public CrystamageSatchel getSatchel() {
            return this.satchel;
        }

        private static ItemStack getStackWithAmount(@Nonnull ItemStack itemstack, int amount) {
            final List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ThemeType.CLICK_INFO.getColor() + "Amount: " + ThemeType.PASSIVE.getColor() + amount);
            itemstack.setLore(lore);
            return itemstack;
        }

    }
}