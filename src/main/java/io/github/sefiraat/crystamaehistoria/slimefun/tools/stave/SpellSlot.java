package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import lombok.Getter;

import javax.annotation.Nullable;

public enum SpellSlot {
    LEFT_CLICK(1),
    RIGHT_CLICK(2),
    SHIFT_LEFT_CLICK(3),
    SHIFT_RIGHT_CLICK(4);

    @Getter
    public static final SpellSlot[] values = values();

    @Getter
    private final int slot;

    SpellSlot(int slot) {
        this.slot = slot;
    }

    @Nullable
    public static SpellSlot getBySlot(int slot) {
        for (SpellSlot spellSlot : values()) {
            if (spellSlot.slot == slot) {
                return spellSlot;
            }
        }
        return null;
    }
}
