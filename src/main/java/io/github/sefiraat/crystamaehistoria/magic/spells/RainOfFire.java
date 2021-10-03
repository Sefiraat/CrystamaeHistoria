package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RainOfFire extends Spell {

    private static final int PROJECTILES_PER_WAVE = 1;

    public RainOfFire() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 1, false, 20, true)
                .makeDamagingSpell(5, true, 0.5, false)
                .makeProjectileSpell(this::fireProjectiles, this::projectileHits, 1, true, 0.5, true)
                .addBeforeProjectileHitEvent(this::beforeProjectileHits)
                .makeTickingSpell(this::fireProjectiles, 9, false, 10, false);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectiles(@NonNull CastInformation castInformation) {
        Location location = castInformation.getCastLocation();

        for (int i = 0; i < (PROJECTILES_PER_WAVE * castInformation.getPowerMulti()); i++) {

            double range = getRange(castInformation);

            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getY() + 20,
                    location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = new MagicProjectile(EntityType.FIREBALL, spawnLocation, castInformation.getCaster());
            Location destination = spawnLocation.clone().subtract(0, 20, 0);
            magicProjectile.setVelocity(destination, 2);

            registerProjectile(magicProjectile.getProjectile(), castInformation);
        }
    }

    public void beforeProjectileHits(@NonNull @NotNull CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(80);
        }
    }

    public void projectileHits(@NonNull CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            damageEntity(livingEntity, castInformation.getCaster(), getDamage(castInformation), castInformation.getDamageLocation(), getProjectileKnockback(castInformation));
        }
    }
}
