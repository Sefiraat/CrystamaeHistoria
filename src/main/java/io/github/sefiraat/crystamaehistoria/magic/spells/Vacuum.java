package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Vacuum extends Spell {

    public Vacuum() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 10, true)
                .makeDamagingSpell(0, false, 0.2, true)
                .makeTickingSpell(this::onTick, 5, false, 20, false)
                .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    public void onTick(@NonNull CastInformation castInformation) {
        pull(castInformation, getKnockback(castInformation));
    }

    public void afterAllTicks(@NonNull CastInformation castInformation) {
        pull(castInformation, getKnockback(castInformation) * 3);
    }

    private void pull(CastInformation castInformation, double amount) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        Location castLocation = caster.getLocation();
        double range = getRange(castInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                pullEntity(castLocation, entity, amount);
                displayParticleEffect(entity, Particle.CRIT, 1, 10);
            }
        }
    }

}
