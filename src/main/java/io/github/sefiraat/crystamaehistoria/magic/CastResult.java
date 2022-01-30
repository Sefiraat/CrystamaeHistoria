package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;

public enum CastResult {
    CAST_SUCCESS("Successful"),
    CAST_FAIL_NO_CRYSTA("Not enough Crystamae in plate"),
    CAST_FAIL_SLOT_EMPTY("No plate in slot"),
    ON_COOLDOWN("Spell on cooldown"),
    SPELL_DISABLED("This spell has been disabled");

    @Getter
    private static final CastResult[] cachedValues = values();
    private final String message;

    CastResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return getMessage(null);
    }

    public String getMessage(String detail) {
        if (detail == null) {
            return message;
        } else {
            return message + ": " + detail;
        }
    }

}
