package io.github.sefiraat.crystamaehistoria.magic.spells.interfaces;

import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractSpell implements Castable {

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull CastDefinition castDefinition) {
        triggerCooldown(castDefinition);
    }

    private void triggerCooldown(@NonNull CastDefinition castDefinition) {
        // TODO Attach cooldown to stave
    }
}
