package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.AbstractSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableTicking;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RainOfFire extends AbstractSpell implements CastableProjectile, CastableTicking {

    private static final int DAMAGE = 5;
    private static final int COOLDOWN = 100;
    private static final double KNOCK_BACK_FORCE = 1;
    private static final double AOE_RANGE = 3;
    private static final int PROJECTILE_NUMBER = 5;
    private static final int WAVES = 10;
    private static final long PERIOD = 5;

    @Override
    public void cast(@NonNull CastDefinition castDefinition) {
        super.cast(castDefinition);

        castDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);
        registerTicker(castDefinition, PERIOD, WAVES);

    }

    @Override
    public void beforeAffect(@NonNull @NotNull CastDefinition castDefinition) {
        for (LivingEntity livingEntity : castDefinition.getAllTargets()) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    public void affect(@NonNull CastDefinition castDefinition) {
        for (LivingEntity livingEntity : castDefinition.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, castDefinition.getCaster(), castDefinition.getDamage(), castDefinition.getDamageLocation(), castDefinition.getKnockbackForce());
        }
    }

    @Override
    public void onTick(@NonNull CastDefinition castDefinition) {
        Location location = castDefinition.getCaster().getLocation();

        for (int i = 0; i < (PROJECTILE_NUMBER * castDefinition.getPowerMulti()); i++) {
            int xOffset = ThreadLocalRandom.current().nextInt(-20, 21);
            int zOffset = ThreadLocalRandom.current().nextInt(-20, 21);
            int yOffset = 20;
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getY() + yOffset,
                    location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = new MagicProjectile(EntityType.FIREBALL, spawnLocation, castDefinition.getCaster());
            Location destination = spawnLocation.clone().subtract(0, yOffset, 0);
            magicProjectile.setVelocity(destination, 2);

            registerProjectile(magicProjectile.getProjectile(), castDefinition);
        }
    }


}
