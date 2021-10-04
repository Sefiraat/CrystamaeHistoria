package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.resource.Skulls;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Materials {

    private final Structure parent;
    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Materials(Structure s, CrystamaeHistoria p) {
        this.parent = s;
        this.plugin = p;
    }

    public void setup() {
        new UnplaceableBlock(
                parent.getItemGroup().crystals,
                new SlimefunItemStack("CRYSTAL_COMMON_MECHANICAL", Skulls.CRYSTAL_COMMON_MECHANICAL.getPlayerHead(), "Historia Crystal", "LORE"),
                RecipeType.SMELTERY,
                new ItemStack[]{}
        ).register(plugin);
    }

}
