package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
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
        SlimefunItemStack stave1 = new SlimefunItemStack("CRY_STAVE_1", Material.STICK, "Stave 1", "DUMMY");
        new Stave(ItemGroups.TOOLS,
            stave1,
            RecipeType.SMELTERY,
            new ItemStack[]{},
            1
        ).register(plugin);
    }

}
