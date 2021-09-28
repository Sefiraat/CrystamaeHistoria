package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.ItemGroup;
import lombok.Getter;

public class Structure {

    @Getter
    private final ItemGroup itemGroup;
    @Getter
    private final Machines machines;
    @Getter
    private final Materials materials;

    public Structure() {

        CrystamaeHistoria plugin = CrystamaeHistoria.inst();

        this.itemGroup = new ItemGroup(plugin);
        this.machines = new Machines(this, plugin);
        this.materials = new Materials(this, plugin);

        setup();
    }

    private void setup() {
        this.itemGroup.setup();
        this.machines.setup();
        this.materials.setup();
    }

}
