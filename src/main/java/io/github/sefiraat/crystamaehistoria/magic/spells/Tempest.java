package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class Tempest extends AbstractDamagingProjectileSpell {

    private static final double PROJECTILE_NUMBER = 10;

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Location location = spellCastInformation.getCaster().getLocation();

        for (int i = 0; i < (PROJECTILE_NUMBER * spellCastInformation.getPowerMulti()); i++) {
            int range = (int) getRange();
            int xOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            int zOffset = ThreadLocalRandom.current().nextInt(-range, range + 1);
            int x = (int) location.getX() + xOffset;
            int z = (int) location.getZ() + zOffset;
            Location spawnLocation = new Location(
                    location.getWorld(),
                    location.getX() + xOffset,
                    location.getWorld().getHighestBlockYAt(x, z),
                    location.getZ() + zOffset
            );

            LightningStrike lightningStrike = spawnLocation.getWorld().strikeLightning(spawnLocation);
            registerProjectile(lightningStrike, spellCastInformation);
        }

    }

    @Override
    protected int getBaseCooldown() {
        return 200;
    }

    @Override
    protected int getBaseDamage() {
        return 3;
    }

    @Override
    protected double getRange() {
        return 10;
    }

    @Override
    protected double getKnockback() {
        return 0;
    }

    @Override
    protected double getProjectileAoeRange() {
        return 2;
    }

    @Override
    protected double getProjectileKnockbackForce() {
        return 2;
    }

    @Override
    public void beforeAffect(@NonNull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    public void affect(@NonNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), spellCastInformation.getDamage(), spellCastInformation.getDamageLocation(), spellCastInformation.getKnockbackForce());
        }
    }

}
