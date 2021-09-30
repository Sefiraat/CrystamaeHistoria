package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractDamagingTickingProjectileSpell extends AbstractDamagingProjectileSpell implements CastableTicking {

    private int numberOfTicks;
    private int tickInterval;

    public AbstractDamagingTickingProjectileSpell() {
        this.numberOfTicks = getNumberTicks();
        this.tickInterval = getTickInterval();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        registerTicker(spellCastInformation, getTickInterval(), getNumberTicks());
    }

    protected abstract int getNumberTicks();

    protected abstract int getTickInterval();
}
