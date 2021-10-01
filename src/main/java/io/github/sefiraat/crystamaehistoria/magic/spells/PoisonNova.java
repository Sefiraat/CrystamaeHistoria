package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class PoisonNova extends Spell {

    public PoisonNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 20, true, 10, true)
                .makeDamagingSpell(1, false, 0, false)
                .makeProjectileSpell(this::fireProjectile, this::projectileHit, 0, false, 0 ,false)
                .addAfterProjectileHitEvent(this::afterProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectile(@Nonnull SpellCastInformation spellCastInformation) {
        double sizeEnd = getRange(spellCastInformation);
        int sizeCast = 1;
        int stepSize = 3;
        Location middle = spellCastInformation.getCastLocation().clone().add(0, 1, 0);
        for(double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 1, dz);
            MagicProjectile magicProjectile = new MagicProjectile(EntityType.ENDER_PEARL, spawn, spellCastInformation.getCaster());
            magicProjectile.setVelocity(destination, 1);

            registerProjectile(magicProjectile.getProjectile(), spellCastInformation);
        }
    }

    public void projectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        LivingEntity hit = spellCastInformation.getMainTarget();
        if (hit.getHealth() == 1) {
            damageEntity(hit, spellCastInformation.getCaster(), getDamage(spellCastInformation));
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, spellCastInformation.getPowerMulti() * 40, spellCastInformation.getPowerMulti() + 1);
            hit.addPotionEffect(potionEffect);
            setLastDamageToCaster(hit, spellCastInformation);
        }
    }

    public void afterProjectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        displayParticleEffect(spellCastInformation.getMainTarget(), Particle.CRIMSON_SPORE, 1.0, 10);
    }
}
