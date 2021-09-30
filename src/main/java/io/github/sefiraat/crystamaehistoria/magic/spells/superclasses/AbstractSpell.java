package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractSpell implements Castable {

    protected int cooldown;

    public AbstractSpell() {
        this.cooldown = getBaseCooldown();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        triggerCooldown(spellCastInformation);
    }

    private void triggerCooldown(@NonNull SpellCastInformation spellCastInformation) {
        // TODO Attach cooldown to stave
    }

    protected abstract int getBaseCooldown();


}
