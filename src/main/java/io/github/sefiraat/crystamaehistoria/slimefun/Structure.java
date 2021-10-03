package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.resource.Skulls;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class Structure {

    @Getter
    private final ItemGroup itemGroup;
    @Getter
    private final Machines machines;
    @Getter
    private final Materials materials;
    @Getter
    private final Tools tools;

    public Structure() {

        CrystamaeHistoria plugin = CrystamaeHistoria.inst();

        this.itemGroup = new ItemGroup(plugin);
        this.machines = new Machines(this, plugin);
        this.materials = new Materials(this, plugin);
        this.tools = new Tools(this, plugin);

        setup();
    }

    private void setup() {
        this.itemGroup.setup();
        this.machines.setup();
        this.materials.setup();
        this.tools.setup();
    }

}
