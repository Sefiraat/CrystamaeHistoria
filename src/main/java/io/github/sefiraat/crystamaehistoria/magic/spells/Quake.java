package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class Quake extends Spell {

    public Quake() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 20, true)
                .makeDamagingSpell(2, true, 0, false)
                .makeTickingSpell(this::onTick, 5, false,20, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Location castLocation = castInformation.getCastLocation().clone();
        double range = getRange(castInformation);

        for (int i = 0; i < range; i++) {
            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double directionalXOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);
            double directionalZOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);
            Location spawnLocation = new Location(
                    castLocation.getWorld(),
                    castLocation.getX() + xOffset,
                    castLocation.getY(),
                    castLocation.getZ() + zOffset
            );

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(90,100,105), 2);
            castLocation.getWorld().spawnParticle(Particle.REDSTONE, spawnLocation, 1, directionalXOffset, 2, directionalZOffset, dustOptions);
        }
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                damageEntity(((LivingEntity) entity), castInformation.getCaster(), getDamage(castInformation));
            }
        }
    }

}
