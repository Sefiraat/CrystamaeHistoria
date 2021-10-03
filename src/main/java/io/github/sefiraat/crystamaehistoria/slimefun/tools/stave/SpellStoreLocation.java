package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import lombok.Getter;

public enum SpellStoreLocation {
    LEFT_CLICK(1),
    RIGHT_CLICK(2),
    SHIT_LEFT_CLICK(3),
    SHIFT_RIGHT_CLICK(4);

    @Getter
    private final int slot;

    SpellStoreLocation(int slot) {
        this.slot = slot;
    }
}
