package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingTickingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RainOfFire extends AbstractDamagingTickingProjectileSpell {

    private static final int PROJECTILES_PER_WAVE = 5;

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
    }

    @Override
    protected int getNumberTicks() {
        return 10;
    }

    @Override
    protected int getTickInterval() {
        return 10;
    }

    @Override
    protected int getBaseCooldown() {
        return 100;
    }

    @Override
    protected int getBaseDamage() {
        return 5;
    }

    @Override
    protected double getRange() {
        return 20;
    }

    @Override
    protected double getKnockback() {
        return 0;
    }

    @Override
    protected double getProjectileAoeRange() {
        return 3;
    }

    @Override
    protected double getProjectileKnockbackForce() {
        return 1;
    }

    @Override
    public void beforeAffect(@NonNull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    public void affect(@NonNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), spellCastInformation.getDamage(), spellCastInformation.getDamageLocation(), spellCastInformation.getKnockbackForce());
        }
    }

    @Override
    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        Location location = spellCastInformation.getCaster().getLocation();

        for (int i = 0; i < (PROJECTILES_PER_WAVE * spellCastInformation.getPowerMulti()); i++) {

            int range = (int) getRange();

            int xOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            int zOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getY() + range,
                    location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = new MagicProjectile(EntityType.FIREBALL, spawnLocation, spellCastInformation.getCaster());
            Location destination = spawnLocation.clone().subtract(0, range, 0);
            magicProjectile.setVelocity(destination, 2);

            registerProjectile(magicProjectile.getProjectile(), spellCastInformation);
        }
    }


}
