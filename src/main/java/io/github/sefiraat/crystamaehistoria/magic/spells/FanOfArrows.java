package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FanOfArrows extends Spell {

    public FanOfArrows() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 20, true, 1, false)
                .makeProjectileSpell(this::fireProjectiles, this::projectileHit, 0, false, 0, false)
                .makeDamagingSpell(1, true, 0, false);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectiles(@Nonnull SpellCastInformation spellCastInformation) {
        Player player = spellCastInformation.getCaster();
        double sizeEnd = getRange(spellCastInformation);
        int sizeCast = 3;
        int stepSize = 5;
        Location middle = player.getLocation().clone().add(0, 1, 0);
        for(double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 5, dz);
            MagicProjectile magicProjectile = new MagicProjectile(EntityType.ARROW, spawn, player);
            magicProjectile.setVelocity(destination, 1);

            registerProjectile(magicProjectile.getProjectile(), spellCastInformation);
        }
    }

    public void projectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        EntityUtils.damageEntity(spellCastInformation.getMainTarget(), spellCastInformation.getCaster(), getDamage(spellCastInformation));
    }

}
