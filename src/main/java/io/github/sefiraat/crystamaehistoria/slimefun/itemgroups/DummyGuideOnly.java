package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyGuideOnly {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_GUIDE_DUMMY",
        new ItemStack(Material.BOOK),
        ThemeType.MECHANISM,
        "Guide",
        "This is a guide only.",
        "Some crafts in Crystamae are very",
        "different compared to other addons",
        "so these are here just for clarification."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.GUIDE_ONLY, STACK);
}
