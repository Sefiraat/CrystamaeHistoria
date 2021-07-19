package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.Categories;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.Machines;
import lombok.Getter;

public class Structure {

    private final CrystamaeHistoria plugin;
    @Getter
    private final Categories categories;
    @Getter
    private final Machines machines;

    public Structure() {
        this.plugin = CrystamaeHistoria.inst();
        this.categories = new Categories(plugin);
        this.machines = new Machines(this, plugin);



        setup();
    }

    private void setup() {
        this.categories.setup();
        this.machines.setup();
    }
}
