package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class Shroud extends Spell {

    private static final int BLIND_DURATION = 300;
    private static final int WITHER_DURATION = 160;

    public Shroud() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, true, 5, true)
                .makeDamagingSpell(0, false, 0, false)
                .makeInstantSpell(this::cast);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.BLINDNESS, 0, BLIND_DURATION);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.WITHER, 0, WITHER_DURATION);
        setSpellCore(spellCoreBuilder.build());
    }

    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        LivingEntity caster = spellCastInformation.getCaster();
        double range = getRange(spellCastInformation);
        for (Entity entity : caster.getWorld().getNearbyEntities(caster.getLocation(), range, range, range)) {
            if (entity instanceof LivingEntity && entity != caster) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity);
                displayParticleEffect(livingEntity, Particle.CLOUD, 2, 2);
            }
        }
    }
}
