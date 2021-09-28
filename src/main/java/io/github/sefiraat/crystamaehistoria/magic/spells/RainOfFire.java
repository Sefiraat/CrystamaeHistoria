package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RainOfFire implements CastableProjectile {

    private static final int DAMAGE = 5;
    private static final int COOLDOWN = 100;
    private static final double KNOCK_BACK_FORCE = 1;
    private static final double AOE_RANGE = 3;
    private static final int PROJECTILE_NUMBER = 10;

    @Override
    public void cast(@NonNull SpellDefinition spellDefinition) {

        spellDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Location location = spellDefinition.getCaster().getLocation();

        for (int i = 0; i < (PROJECTILE_NUMBER * spellDefinition.getPowerMulti()); i++) {
            int xOffset = ThreadLocalRandom.current().nextInt(-20, 21);
            int zOffset = ThreadLocalRandom.current().nextInt(-20, 21);
            int yOffset = 20;
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getY() + yOffset,
                    location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = new MagicProjectile(EntityType.FIREBALL, spawnLocation, spellDefinition.getCaster());
            Location destination = spawnLocation.clone().subtract(0, yOffset, 0);
            magicProjectile.setVelocity(destination, 2);

            registerProjectile(magicProjectile.getProjectile(), spellDefinition);

        }
    }

    @Override
    public void beforeAffect(@NonNull @NotNull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    public void affect(@NonNull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellDefinition.getCaster(), spellDefinition.getDamage(), spellDefinition.getDamageLocation(), spellDefinition.getKnockbackForce());
        }
    }

}
