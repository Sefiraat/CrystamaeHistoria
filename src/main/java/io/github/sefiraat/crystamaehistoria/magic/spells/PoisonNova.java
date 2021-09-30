package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class PoisonNova extends AbstractDamagingProjectileSpell {

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Player player = spellCastInformation.getCaster();
        int sizeEnd = (int) getRange();
        int sizeCast = 1;
        int stepSize = 3;
        Location middle = player.getLocation().clone().add(0, 1, 0);
        for(double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 1, dz);
            MagicProjectile magicProjectile = new MagicProjectile(EntityType.ENDER_PEARL, spawn, player);
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
        return 0;
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
        return 0;
    }

    @Override
    protected double getProjectileKnockbackForce() {
        return 0;
    }

    @Override
    public void affect(@Nonnull SpellCastInformation spellCastInformation) {
        LivingEntity hit = spellCastInformation.getMainTarget();
        if (hit.getHealth() == 1) {
            hit.damage(1, spellCastInformation.getCaster());
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, spellCastInformation.getPowerMulti() * 40, spellCastInformation.getPowerMulti() + 1);
            hit.addPotionEffect(potionEffect);
            setLastDamageToCaster(hit, spellCastInformation);
        }
    }

    @Override
    public void afterAffect(@Nonnull SpellCastInformation spellCastInformation) {
        displayParticleEffect(spellCastInformation.getMainTarget(), Particle.CRIMSON_SPORE, 1.0, 10);
    }
}
