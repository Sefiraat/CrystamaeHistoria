package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
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

    public void fireProjectiles(@NonNull SpellCastInformation spellCastInformation) {
        Location location = spellCastInformation.getCastLocation();

        for (int i = 0; i < (PROJECTILES_PER_WAVE * spellCastInformation.getPowerMulti()); i++) {

            int range = (int) getRange(spellCastInformation);

            int xOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            int zOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getY() + 20,
                    location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = new MagicProjectile(EntityType.FIREBALL, spawnLocation, spellCastInformation.getCaster());
            Location destination = spawnLocation.clone().subtract(0, 20, 0);
            magicProjectile.setVelocity(destination, 2);

            registerProjectile(magicProjectile.getProjectile(), spellCastInformation);
        }
    }

    public void beforeProjectileHits(@NonNull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)) {
            livingEntity.setFireTicks(80);
        }
    }

    public void projectileHits(@NonNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)) {
            damageEntity(livingEntity, spellCastInformation.getCaster(), getDamage(spellCastInformation), spellCastInformation.getDamageLocation(), getProjectileKnockback(spellCastInformation));
        }
    }
}
