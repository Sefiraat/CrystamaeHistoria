package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.AbstractSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableTicking;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

public class Quake extends AbstractSpell implements CastableTicking {

    private static final int DAMAGE = 1;
    private static final int COOLDOWN = 100;
    private static final double KNOCK_BACK_FORCE = 0;
    private static final double AOE_RANGE = 5;
    private static final int WAVES = 5;
    private static final long PERIOD = 20;
    private static final int IMPACT_ZONES = 100;
    private static final int RANGE = 30;

    @Override
    public void cast(@Nonnull CastDefinition castDefinition) {
        super.cast(castDefinition);

        castDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);
        registerTicker(castDefinition, PERIOD, WAVES);

    }

    @Override
    public void onTick(@NonNull CastDefinition castDefinition) {
        Location castLocation = castDefinition.getCastLocation().clone();

        for (int i = 0; i < IMPACT_ZONES; i++) {
            double xOffset = ThreadLocalRandom.current().nextDouble(-RANGE, RANGE + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-RANGE, RANGE + 1);

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
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, RANGE, 2, RANGE)) {
            if (entity instanceof LivingEntity && entity != castDefinition.getCaster()) {
                EntityUtils.damageEntity(((LivingEntity) entity), castDefinition.getCaster(), castDefinition.getDamage());
            }
        }
    }

    @Override
    public void afterAllTicks(@NonNull CastDefinition castDefinition) {

    }

}
