package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.NonNull;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Map;

public abstract class AbstractPositiveSpell extends AbstractSpell {

    protected int heal;
    protected double range;

    public AbstractPositiveSpell() {
        this.heal = getBaseHealing();
        this.range = getRange();
    }

    @OverridingMethodsMustInvokeSuper
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
    }

    protected abstract int getBaseHealing();

    protected int getHealAmount(SpellCastInformation spellCastInformation) {
        return getBaseHealing() * spellCastInformation.getPowerMulti();
    }

    protected abstract double getRange();

    protected void healEntity(LivingEntity livingEntity, double healAmount) {
        AttributeInstance attribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            livingEntity.setHealth(Math.min(attribute.getValue(), livingEntity.getHealth() + healAmount));
        }
    }

}
