package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class Shroud extends Spell {

    private static final int BLIND_DURATION = 15;
    private static final int WITHER_DURATION = 5;

    public Shroud() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, true, 5, true)
                .makeDamagingSpell(0, false, 0, false)
                .makeInstantSpell(this::cast);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.BLINDNESS, 0, BLIND_DURATION);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.WITHER, 0, WITHER_DURATION);
        setSpellCore(spellCoreBuilder.build());
    }

    public void cast(@NonNull CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity);
                displayParticleEffect(livingEntity, Particle.SLIME, 2, 2);
            }
        }
    }
}
