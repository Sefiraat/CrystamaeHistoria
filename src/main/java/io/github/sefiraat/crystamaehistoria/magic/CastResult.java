package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;

public enum CastResult {
    CAST_SUCCESS("Successful"),
    CAST_FAIL_NO_CRYSTA("Not enough Crystamae in plate"),
    CAST_FAIL_SLOT_EMPTY("No plate in slot"),
    ON_COOLDOWN("Spell on cooldown");

    @Getter
    protected static final CastResult[] cachedValues = values();
    @Getter
    private final String message;

    CastResult(String message) {
        this.message = message;
    }
}
