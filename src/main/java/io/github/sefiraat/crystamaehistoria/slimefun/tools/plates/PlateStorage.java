package io.github.sefiraat.crystamaehistoria.slimefun.tools.plates;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import lombok.Getter;

@Getter
public class PlateStorage {

    private final int tier;
    private final SpellType storedSpell;
    private final int charges;

    public PlateStorage(int tier, SpellType storedSpell, int charges) {
        this.tier = tier;
        this.storedSpell = storedSpell;
        this.charges = charges;
    }

}
