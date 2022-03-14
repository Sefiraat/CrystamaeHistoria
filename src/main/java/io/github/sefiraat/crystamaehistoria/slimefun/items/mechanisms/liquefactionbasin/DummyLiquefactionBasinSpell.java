package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyLiquefactionBasinSpell {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_LIQUEFACTION_BASIN_DUMMY_SPELL",
        new ItemStack(Material.CAULDRON),
        ThemeType.MECHANISM,
        "Liquefaction Basin",
        "Formed by dropping a basic plate into a",
        "filled Liquefaction Basin containing",
        "the correct Crystamae."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.LIQUEFACTION_DUMMY_SPELL, STACK);
}
