package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DummyLiquefactionBasin {

    public static final SlimefunItemStack STACK = ThemeType.themeStack(
        "CRY_LIQUEFACTION_BASIN_DUMMY_SPELL",
        new ItemStack(Material.CAULDRON),
        ThemeType.MECHANISM,
        "Liquefaction Basin",
        "Formed by dropping a basic plate into a",
        "filled Liquefaction Basin containing",
        "the correct Crystamae."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.REALISATION_DUMMY_CRYSTAL, STACK);

    private DummyLiquefactionBasin() {
        throw new IllegalStateException("Utility class");
    }
}
