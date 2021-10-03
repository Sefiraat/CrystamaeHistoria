package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import lombok.Getter;

import javax.annotation.Nullable;

public class SpellInStaveInstance {

    @Getter
    @Nullable
    private SpellType spell;
    @Getter
    private int charges;
    @Getter
    private int cooldown;

}
