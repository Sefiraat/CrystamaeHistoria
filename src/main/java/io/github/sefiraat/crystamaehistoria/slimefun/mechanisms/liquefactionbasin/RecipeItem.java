package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeItem {

    @Getter
    private final ItemStack inputItem;
    private final List<StoryType> storyTypes;
    private final List<Integer> amounts;

    @ParametersAreNonnullByDefault
    public RecipeItem(
        ItemStack inputItem,
        StoryType type1, int crysta1,
        StoryType type2, int crysta2,
        StoryType type3, int crysta3
    ) {
        this.inputItem = inputItem;
        this.storyTypes = Arrays.asList(type1, type2, type3);
        this.amounts = Arrays.asList(crysta1, crysta2, crysta3);
    }

    public boolean recipeMatches(List<StoryType> testTypes, List<Integer> testAmounts, ItemStack inputItem) {
        int i = 0;
        if (!SlimefunUtils.isItemSimilar(this.inputItem, inputItem, true,false)) {
            return false;
        }
        for (StoryType testType : testTypes) {
            int index = storyTypes.indexOf(testType);
            if (index == -1 || amounts.get(index) > testAmounts.get(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public ItemStack[] getDisplayRecipe() {
        return new ItemStack[]{
            null, inputItem, null,
            getDisplayCrystal(0), getDisplayCrystal(1), getDisplayCrystal(2),
            null, null, null
        };
    }

    public ItemStack getDisplayCrystal(int index) {
        ItemStack itemStack = Materials.getDummyCrystalMap().get(storyTypes.get(index)).getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        list.add(ThemeType.CLICK_INFO.getColor() + "Minimum amount: " + ThemeType.PASSIVE.getColor() + amounts.get(index));
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public StoryType getInputType(int index) {
        return this.storyTypes.get(index);
    }

    public int getInputAmount(int index) {
        return this.amounts.get(index);
    }
}
