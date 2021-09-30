package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FanOfArrows extends AbstractDamagingProjectileSpell {

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Player player = spellCastInformation.getCaster();
        int sizeEnd = (int) getRange();
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

    @Override
    protected int getBaseCooldown() {
        return 10;
    }

    @Override
    protected int getBaseDamage() {
        return 1;
    }

    @Override
    protected double getRange() {
        return 30;
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
        EntityUtils.damageEntity(spellCastInformation.getMainTarget(), spellCastInformation.getCaster(), spellCastInformation.getDamage());
    }

}
