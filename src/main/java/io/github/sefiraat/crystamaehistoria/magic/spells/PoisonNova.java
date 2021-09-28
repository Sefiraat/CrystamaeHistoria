package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
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

public class PoisonNova implements CastableProjectile {

    private static final int DAMAGE = 2;
    private static final int COOLDOWN = 5;
    private static final double KNOCK_BACK_FORCE = 0;
    private static final double AOE_RANGE = 1;
    private static final int EFFECT_DURATION = 40;

    @Override
    public void cast(@Nonnull SpellDefinition spellDefinition) {

        spellDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Player player = spellDefinition.getCaster();
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

            registerProjectile(magicProjectile.getProjectile(), spellDefinition);
        }

    }

    @Override
    public void affect(@Nonnull SpellDefinition spellDefinition) {
        LivingEntity hit = spellDefinition.getMainTarget();
        if (hit.getHealth() == 1) {
            hit.damage(1, spellDefinition.getCaster());
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, spellDefinition.getPowerMulti() * EFFECT_DURATION, spellDefinition.getPowerMulti() + 1);
            hit.addPotionEffect(potionEffect);
            setLastDamageToCaster(hit, spellDefinition);
        }
    }

    @Override
    public void afterAffect(@Nonnull SpellDefinition spellDefinition) {
        displayParticleEffect(spellDefinition.getMainTarget(), Particle.CRIMSON_SPORE, 1.0, 10);
    }
}
