package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.AbstractSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class PoisonNova extends AbstractSpell implements CastableProjectile {

    private static final int DAMAGE = 2;
    private static final int COOLDOWN = 5;
    private static final double KNOCK_BACK_FORCE = 0;
    private static final double AOE_RANGE = 1;
    private static final int EFFECT_DURATION = 40;

    @Override
    public void cast(@Nonnull CastDefinition castDefinition) {
        super.cast(castDefinition);

        castDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Player player = castDefinition.getCaster();
        int sizeEnd = 20;
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

            registerProjectile(magicProjectile.getProjectile(), castDefinition);
        }

    }

    @Override
    public void affect(@Nonnull CastDefinition castDefinition) {
        LivingEntity hit = castDefinition.getMainTarget();
        if (hit.getHealth() == 1) {
            hit.damage(1, castDefinition.getCaster());
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, castDefinition.getPowerMulti() * EFFECT_DURATION, castDefinition.getPowerMulti() + 1);
            hit.addPotionEffect(potionEffect);
            setLastDamageToCaster(hit, castDefinition);
        }
    }

    @Override
    public void afterAffect(@Nonnull CastDefinition castDefinition) {
        displayParticleEffect(castDefinition.getMainTarget(), Particle.CRIMSON_SPORE, 1.0, 10);
    }
}
