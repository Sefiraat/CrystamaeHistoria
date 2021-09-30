package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractDamagingSpell extends AbstractSpell {

    protected int damage;
    protected double range;
    protected double knockback;

    public AbstractDamagingSpell() {
        this.damage = getBaseDamage();
        this.range = getRange();
        this.knockback = getKnockback();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
    }

    protected abstract int getBaseDamage();

    protected abstract double getRange();

    protected abstract double getKnockback();

}
