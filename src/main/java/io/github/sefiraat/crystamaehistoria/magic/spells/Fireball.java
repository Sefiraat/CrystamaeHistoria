package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class Fireball extends AbstractDamagingProjectileSpell {

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Location location = spellCastInformation.getCaster().getLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = new MagicProjectile(EntityType.SMALL_FIREBALL, aimLocation, spellCastInformation.getCaster());
        magicProjectile.setVelocity(location.getDirection(), 2);

        registerProjectile(magicProjectile.getProjectile(), spellCastInformation);

    }

    @Override
    protected int getBaseCooldown() {
        return 5;
    }

    @Override
    protected int getBaseDamage() {
        return 1;
    }

    @Override
    protected double getRange() {
        return 0;
    }

    @Override
    protected double getKnockback() {
        return 0;
    }

    @Override
    protected double getProjectileAoeRange() {
        return 1;
    }

    @Override
    protected double getProjectileKnockbackForce() {
        return 1;
    }

    @Override
    public void beforeAffect(@Nonnull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    public void affect(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), spellCastInformation.getDamage(), spellCastInformation.getDamageLocation(), spellCastInformation.getKnockbackForce());
        }
    }

}
