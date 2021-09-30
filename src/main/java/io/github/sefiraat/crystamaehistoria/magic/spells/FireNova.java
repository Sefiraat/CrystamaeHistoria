package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FireNova extends AbstractDamagingProjectileSpell {

    private static final int DAMAGE = 3;
    private static final int COOLDOWN = 5;
    private static final double KNOCK_BACK_FORCE = 1;
    private static final double AOE_RANGE = 2;

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Player player = spellCastInformation.getCaster();
        int sizeEnd = (int) getRange();
        int sizeCast = 2;
        int stepSize = 3;
        Location middle = player.getLocation().clone().add(0, 1, 0);
        for(double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 0, dz);
            MagicProjectile magicProjectile = new MagicProjectile(EntityType.SMALL_FIREBALL, spawn, player);
            magicProjectile.setVelocity(destination, 1);

            registerProjectile(magicProjectile.getProjectile(), spellCastInformation);
        }

    }

    @Override
    protected int getBaseCooldown() {
        return 20;
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
        return 1;
    }

    @Override
    public void affect(@Nonnull SpellCastInformation spellCastInformation) {
        EntityUtils.damageEntity(spellCastInformation.getMainTarget(), spellCastInformation.getCaster(), spellCastInformation.getDamage());
        for (LivingEntity livingEntity : spellCastInformation.getAdditionalTargets()) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), spellCastInformation.getDamage());
        }
    }

    @Override
    public void afterAffect(@Nonnull SpellCastInformation spellCastInformation) {
        displayParticleEffect(spellCastInformation.getMainTarget(), Particle.EXPLOSION_NORMAL, 1.0, 5);
    }
}
