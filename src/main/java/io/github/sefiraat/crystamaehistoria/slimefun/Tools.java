package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Tools {

    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Tools(CrystamaeHistoria p) {
        this.plugin = p;
    }

    public void setup() {
        new Stave(
            ItemGroups.TOOLS,
            ThemeType.themeStack(
                "CRY_STAVE_1",
                new ItemStack(Material.STICK),
                ThemeType.STAVE,
                "Basic Stave",
                "A stave with the ability to hold",
                "magically charged plates."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{},
            1
        ).register(plugin);
    }
}
