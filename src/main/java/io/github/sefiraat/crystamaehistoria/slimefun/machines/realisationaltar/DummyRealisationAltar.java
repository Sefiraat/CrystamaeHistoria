package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DummyRealisationAltar {

    public static final SlimefunItemStack STACK = ThemeUtils.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_DUMMY_CRYSTAL",
            new ItemStack(Material.CHISELED_DEEPSLATE),
            ThemeType.MECHANISM,
            "Realisation Altar",
            "Crystal are gathered when mining the fully",
            "formed crystals that gather around an active",
            "Altar. Giving the Altar storied blocks will",
            "produce crystals."
    );
    public static final RecipeType TYPE = new RecipeType(CrystamaeHistoria.getKeys().getRealisationDummyCrystal(), STACK);

    private DummyRealisationAltar() {
        throw new IllegalStateException("Utility class");
    }


}
