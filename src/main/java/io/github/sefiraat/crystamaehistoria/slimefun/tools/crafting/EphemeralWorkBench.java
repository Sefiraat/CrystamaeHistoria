package io.github.sefiraat.crystamaehistoria.slimefun.tools.crafting;

import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class EphemeralWorkBench extends SlimefunItem {

    private static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 14, 15, 16, 17, 18, 22, 24, 26, 27, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    private static final int[] RECIPE_SLOTS = {
        10, 11, 12, 19, 20, 21, 28, 29, 30
    };
    private static final int CRAFT_SLOT = 23;
    private static final int OUTPUT_SLOT = 25;

    private static final CustomItemStack CRAFT_BUTTON_STACK = new CustomItemStack(
        Material.CRAFTING_TABLE,
        ThemeType.CLICK_INFO.getColor() + "Click to craft"
    );

    private static final Map<ItemStack[], ItemStack> RECIPES = new HashMap<>();

    static {
        for (SlimefunItem i : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            RecipeType recipeType = i.getRecipeType();
            if ((recipeType == RecipeType.ENHANCED_CRAFTING_TABLE) && allowedRecipe(i)) {
                addRecipe(i.getRecipe(), i.getRecipeOutput());
            }
        }
    }

    @ParametersAreNonnullByDefault
    public EphemeralWorkBench(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(onItemUse());
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            e.cancel();
            WorkBenchMenu workBenchMenu = new WorkBenchMenu();
            workBenchMenu.open(e.getPlayer());
        };
    }



    public static boolean allowedRecipe(SlimefunItemStack i) {
        return allowedRecipe(i.getItemId());
    }

    public static boolean allowedRecipe(String s) {
        return !isBackpack(s);
    }

    public static boolean isBackpack(String s) {
        return s.matches("(.*)BACKPACK(.*)");
    }

    public static boolean allowedRecipe(SlimefunItem i) {
        return allowedRecipe(i.getId());
    }

    public static void addRecipe(ItemStack[] input, ItemStack output) {
        RECIPES.put(input, output);
    }

    public class WorkBenchMenu extends ChestMenu {

        public WorkBenchMenu() {
            super("Ephemeral Workbench");
            ChestMenuUtils.drawBackground(this, BACKGROUND_SLOTS);
            addPlayerInventoryClickHandler((p, slot, item, action) -> true);
            addItem(CRAFT_SLOT, CRAFT_BUTTON_STACK, (p, slot, item, action) -> false);
            addMenuClickHandler(CRAFT_SLOT, (player, slot, item, action) -> {
                final ItemStack[] inputs = new ItemStack[RECIPE_SLOTS.length];
                int i = 0;

                // Fill the inputs - abort if storied
                for (int recipeSlot : RECIPE_SLOTS) {
                    ItemStack stack = getItemInSlot(recipeSlot);
                    if (stack != null && stack.getType() != Material.AIR && StoryUtils.isStoried(stack)) {
                        return false;
                    }
                    inputs[i] = stack;
                    i++;
                }

                ItemStack crafted = null;

                // Go through each recipe, test and set the ItemStack if found
                for (Map.Entry<ItemStack[], ItemStack> entry : RECIPES.entrySet()) {
                    if (testRecipe(inputs, entry.getKey())) {
                        crafted = entry.getValue().clone();
                        break;
                    }
                }

                if (crafted == null) {
                    crafted = Bukkit.craftItem(inputs, player.getWorld(), player);
                }

                if (crafted.getType() != Material.AIR && fits(crafted, OUTPUT_SLOT)) {
                    pushAndClear(crafted);
                }

                return false;
            });

            addMenuCloseHandler(p -> {
                dropItems(p.getLocation(), RECIPE_SLOTS);
                dropItems(p.getLocation(), OUTPUT_SLOT);
            });
        }

        private boolean testRecipe(ItemStack[] input, ItemStack[] recipe) {
            for (int test = 0; test < recipe.length; test++) {
                if (!SlimefunUtils.isItemSimilar(input[test], recipe[test], true, false)) {
                    return false;
                }
            }
            return true;
        }

        // Taken from DirtyChestMenu - Slimefun4
        public boolean fits(@Nonnull ItemStack item, int... slots) {
            for (int slot : slots) {
                // A small optimization for empty slots
                if (getItemInSlot(slot) == null) {
                    return true;
                }
            }

            return InvUtils.fits(toInventory(), ItemStackWrapper.wrap(item), slots);
        }

        private void pushAndClear(ItemStack itemStack) {
            pushItem(itemStack, OUTPUT_SLOT);
            for (int recipeSlot : RECIPE_SLOTS) {
                if (getItemInSlot(recipeSlot) != null) {
                    ItemUtils.consumeItem(getItemInSlot(recipeSlot), 1, true);
                }
            }
        }

        // Taken from BlockMenu - Slimefun4
        public void dropItems(Location l, int... slots) {
            for (int slot : slots) {
                ItemStack item = getItemInSlot(slot);

                if (item != null) {
                    l.getWorld().dropItemNaturally(l, item);
                    replaceExistingItem(slot, null);
                }
            }
        }

        // Taken from DirtyChestMenu - Slimefun4
        @Nullable
        public ItemStack pushItem(ItemStack item, int... slots) {
            if (item == null || item.getType() == Material.AIR) {
                throw new IllegalArgumentException("Cannot push null or AIR");
            }

            ItemStackWrapper wrapper = null;
            int amount = item.getAmount();

            for (int slot : slots) {
                if (amount <= 0) {
                    break;
                }

                ItemStack stack = getItemInSlot(slot);

                if (stack == null) {
                    replaceExistingItem(slot, item);
                    return null;
                } else {
                    int maxStackSize = Math.min(stack.getMaxStackSize(), toInventory().getMaxStackSize());
                    if (stack.getAmount() < maxStackSize) {
                        if (wrapper == null) {
                            wrapper = ItemStackWrapper.wrap(item);
                        }

                        if (ItemUtils.canStack(wrapper, stack)) {
                            amount -= (maxStackSize - stack.getAmount());
                            stack.setAmount(Math.min(stack.getAmount() + item.getAmount(), maxStackSize));
                            item.setAmount(amount);
                        }
                    }
                }
            }

            if (amount > 0) {
                return new CustomItemStack(item, amount);
            } else {
                return null;
            }
        }
    }


}
