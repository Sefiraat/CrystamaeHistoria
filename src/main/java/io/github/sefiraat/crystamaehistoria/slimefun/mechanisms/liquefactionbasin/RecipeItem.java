package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class RecipeItem {

    @Getter
    private final ItemStack inputItem;
    private final List<StoryType> storyTypes;
    private final List<Integer> amounts;
    @Nullable
    private final Predicate<Player> additionalRequirement;

    @ParametersAreNonnullByDefault
    public RecipeItem(
        ItemStack inputItem,
        StoryType type1, int crysta1,
        StoryType type2, int crysta2,
        StoryType type3, int crysta3
    ) {
        this(inputItem, type1, crysta1, type2, crysta2, type3, crysta3, null);
    }

    @ParametersAreNonnullByDefault
    public RecipeItem(
        ItemStack inputItem,
        StoryType type1, int crysta1,
        StoryType type2, int crysta2,
        StoryType type3, int crysta3,
        @Nullable Predicate<Player> additionalRequirement
    ) {
        this.inputItem = inputItem;
        this.storyTypes = Arrays.asList(type1, type2, type3);
        this.amounts = Arrays.asList(crysta1, crysta2, crysta3);
        this.additionalRequirement = additionalRequirement;
    }

    @ParametersAreNonnullByDefault
    public boolean recipeMatches(List<StoryType> testTypes, List<Integer> testAmounts, ItemStack inputItem, UUID uuid) {
        int i = 0;
        if (uuid != null && additionalRequirement != null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            if (offlinePlayer.isOnline() && !additionalRequirement.test(Bukkit.getPlayer(uuid))) {
                return false;
            }
        }
        if (!SlimefunUtils.isItemSimilar(this.inputItem, inputItem, true, false)) {
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
