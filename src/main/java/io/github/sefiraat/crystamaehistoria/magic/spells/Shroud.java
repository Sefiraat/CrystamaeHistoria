package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractPositiveSpell;
import lombok.NonNull;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class Shroud extends AbstractDamagingSpell {

    private static final int BLIND_DURATION = 300;
    private static final int WITHER_DURATION = 160;

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        LivingEntity caster = spellCastInformation.getCaster();

        addNegativeEffect(PotionEffectType.BLINDNESS, 0, BLIND_DURATION);
        addNegativeEffect(PotionEffectType.WITHER, 0, WITHER_DURATION);

        for (Entity entity : caster.getWorld().getNearbyEntities(caster.getLocation(), getRange(), getRange(), getRange())) {
            if (entity instanceof LivingEntity && entity != caster) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity);
                displayParticleEffect(livingEntity, Particle.CLOUD, 2, 2);
            }
        }
    }

    @Override
    protected int getBaseDamage() {
        return 0;
    }

    @Override
    protected double getRange() {
        return 10;
    }

    @Override
    protected double getKnockback() {
        return 0;
    }

    @Override
    protected int getBaseCooldown() {
        return 40;
    }

}
