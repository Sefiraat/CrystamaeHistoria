package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.Castable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Spell {

    LIGHTNING_CALL("LIGHTNING_CALL", new CallLightning()),
    FAN_OF_ARROWS("FAN_OF_ARROWS", new FanOfArrows()),
    FIREBALL("FIREBALL", new Fireball()),
    POISON_NOVA("POISON_NOVA", new PoisonNova()),
    RAIN_OF_FIRE("RAIN_OF_FIRE", new RainOfFire()),
    TELEPORT("TELEPORT", new Teleport()),
    TEMPEST("TEMPEST", new Tempest()),
    FIRE_NOVA("FIRE_NOVA", new FireNova());

    private final String id;
    private final Castable castable;

    Spell(String id, Castable castable) {
        this.id = id;
        this.castable = castable;
    }

    @Nonnull
    public Castable get() {
        return castable;
    }

    public void cast(SpellDefinition spellDefinition) {
        this.castable.cast(spellDefinition);
    }

    @Nullable
    public static Castable getById(String id) {
        for (Spell spell : values()) {
            if (spell.id.equals(id)) {
                return spell.castable;
            }
        }
        return null;
    }

}
