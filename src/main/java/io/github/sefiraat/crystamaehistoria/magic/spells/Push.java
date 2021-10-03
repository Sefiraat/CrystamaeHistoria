package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Push extends Spell {

    public Push() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 10, true)
                .makeDamagingSpell(0, false, 0.2, true)
                .makeTickingSpell(this::onTick, 5, false, 20, false)
                .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    public void onTick(@NonNull CastInformation castInformation) {
        push(castInformation, getKnockback(castInformation));
    }

    public void afterAllTicks(@NonNull CastInformation castInformation) {
        push(castInformation, getKnockback(castInformation) * 3);
    }

    private void push(CastInformation castInformation, double amount) {
        Location castLocation = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                pushEntity(castLocation, entity, amount);
                displayParticleEffect(entity, Particle.CRIT, 1, 10);
            }
        }
    }

}
