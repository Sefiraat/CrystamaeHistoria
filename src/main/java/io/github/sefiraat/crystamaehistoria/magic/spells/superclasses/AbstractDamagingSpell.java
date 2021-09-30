package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import lombok.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractDamagingSpell implements Castable {

    protected int cooldown;
    protected int damage;
    protected double range;
    protected double knockback;

    public AbstractDamagingSpell() {
        this.cooldown = getBaseCooldown();
        this.damage = getBaseDamage();
        this.range = getRange();
        this.knockback = getKnockback();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        triggerCooldown(spellCastInformation);
    }

    private void triggerCooldown(@NonNull SpellCastInformation spellCastInformation) {
        // TODO Attach cooldown to stave
    }

    protected abstract int getBaseCooldown();

    protected abstract int getBaseDamage();

    protected abstract double getRange();

    protected abstract double getKnockback();

}
