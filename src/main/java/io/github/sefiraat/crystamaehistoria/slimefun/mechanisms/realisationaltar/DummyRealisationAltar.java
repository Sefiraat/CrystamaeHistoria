package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyRealisationAltar {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_REALISATION_ALTAR_DUMMY_CRYSTAL",
        new ItemStack(Material.CHISELED_DEEPSLATE),
        ThemeType.MECHANISM,
        "Realisation Altar",
        "Crystals are gathered when mining the fully",
        "formed crystals that gather around an active",
        "Altar. Giving the Altar storied blocks will",
        "produce crystals."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.REALISATION_DUMMY_CRYSTAL, STACK);
}
