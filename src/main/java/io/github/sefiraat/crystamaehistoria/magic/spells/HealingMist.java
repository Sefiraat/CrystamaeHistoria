package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractPositiveSpell;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class HealingMist extends AbstractPositiveSpell {

    private static final int REGEN_DURATION = 600;

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        LivingEntity caster = spellCastInformation.getCaster();

        addPositiveEffect(PotionEffectType.REGENERATION, 0, REGEN_DURATION);

        for (Entity entity : caster.getWorld().getNearbyEntities(caster.getLocation(), getRange(), getRange(), getRange())) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyPositiveEffects(livingEntity);
            }
        }
    }

    @Override
    protected int getBaseHealing() {
        return 0;
    }

    @Override
    protected double getRange() {
        return 10;
    }

    @Override
    protected int getBaseCooldown() {
        return 2000;
    }

}
