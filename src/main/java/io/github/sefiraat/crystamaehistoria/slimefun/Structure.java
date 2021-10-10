package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.ItemGroups;
import lombok.Getter;

public class Structure {

    @Getter
    private final Machines machines;
    @Getter
    private final Materials materials;
    @Getter
    private final Tools tools;

    public Structure() {

        CrystamaeHistoria plugin = CrystamaeHistoria.inst();

        this.machines = new Machines(this, plugin);
        this.materials = new Materials(this, plugin);
        this.tools = new Tools(this, plugin);

        setup();
    }

    private void setup() {
        ItemGroups.setup();

        this.machines.setup();
        this.materials.setup();
        this.tools.setup();
    }

}
