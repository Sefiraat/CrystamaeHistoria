package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
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

        this.machines = new Machines(plugin);
        this.materials = new Materials(plugin);
        this.tools = new Tools(plugin);

        setup();
    }

    private void setup() {
        this.machines.setup();
        this.materials.setup();
        this.tools.setup();
    }

}
