package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractPositiveTickingProjectileSpell extends AbstractPositiveTickingSpell implements CastableProjectile {

    private double projectileAoeRange;
    private double projectileKnockbackAmount;

    public AbstractPositiveTickingProjectileSpell() {
        this.projectileAoeRange = getProjectileAoeRange();
        this.projectileKnockbackAmount = getProjectileKnockbackForce();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        registerTicker(spellCastInformation, getTickInterval(), getNumberTicks());
    }

    protected abstract double getProjectileAoeRange();

    protected abstract double getProjectileKnockbackForce();

}
