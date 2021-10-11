package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class SpellInstance {

    @Getter
    @Nullable
    private final SpellType spell;
    @Getter
    @Setter
    private int crysta;
    @Getter
    @Setter
    private long nextCast;

    @ParametersAreNonnullByDefault
    public SpellInstance(@Nullable SpellType spell, int crysta, long nextCast) {
        this.spell = spell;
        this.crysta = crysta;
        this.nextCast = nextCast;
    }

}
