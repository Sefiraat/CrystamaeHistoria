package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractPositiveProjectileSpell extends AbstractPositiveSpell implements CastableProjectile {

    private double projectileAoeRange;
    private double projectileKnockbackAmount;

    public AbstractPositiveProjectileSpell() {
        this.projectileAoeRange = getProjectileAoeRange();
        this.projectileKnockbackAmount = getProjectileKnockbackForce();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        spellCastInformation.setProjectileInformation(getBaseHealing(), getProjectileAoeRange(), getProjectileKnockbackForce());
    }

    protected abstract double getProjectileAoeRange();

    protected abstract double getProjectileKnockbackForce();

}
