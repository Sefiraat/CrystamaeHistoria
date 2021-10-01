package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Vacuum extends Spell {

    public Vacuum() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 10, true)
                .makeDamagingSpell(0, false, 0.2, true)
                .makeTickingSpell(this::onTick, 5, false, 20, false)
                .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        pull(spellCastInformation, getKnockback(spellCastInformation));
    }

    public void afterAllTicks(@NonNull SpellCastInformation spellCastInformation) {
        pull(spellCastInformation, getKnockback(spellCastInformation) * 3);
    }

    private void pull(SpellCastInformation spellCastInformation, double amount) {
        Location castLocation = spellCastInformation.getCaster().getLocation();
        double range = getRange(spellCastInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity != spellCastInformation.getCaster()) {
                pullEntity(castLocation, entity, amount);
                displayParticleEffect(entity, Particle.CRIT, 1, 10);
            }
        }
    }

}
