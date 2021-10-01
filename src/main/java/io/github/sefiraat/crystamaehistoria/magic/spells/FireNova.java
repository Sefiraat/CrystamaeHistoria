package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FireNova extends Spell {

    public FireNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, false, 10, false)
                .makeDamagingSpell(3, true, 1, false)
                .makeProjectileSpell(this::fireProjectiles, this::projectileHit, 2, false, 1, false)
                .addAfterProjectileHitEvent(this::afterProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectiles(@Nonnull SpellCastInformation spellCastInformation) {
        Player player = spellCastInformation.getCaster();
        double sizeEnd = getRange(spellCastInformation);
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

    public void projectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, true)) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), getDamage(spellCastInformation));
        }
    }

    public void afterProjectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        displayParticleEffect(spellCastInformation.getMainTarget(), Particle.EXPLOSION_NORMAL, 1.0, 5);
    }
}
