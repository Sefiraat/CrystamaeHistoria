package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class FanOfArrows extends Spell {

    public FanOfArrows() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 20, true, 1, false)
            .makeProjectileSpell(this::fireProjectiles, this::projectileHit, 0, false, 0, false)
            .makeDamagingSpell(1, true, 0, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        double sizeEnd = getRange(castInformation);
        int sizeCast = 3;
        int stepSize = 5;
        Location middle = castInformation.getCastLocation().clone().add(0, 1, 0);
        for (double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 5, dz);
            MagicProjectile magicProjectile = new MagicProjectile(EntityType.ARROW, spawn, castInformation.getCaster());
            magicProjectile.setVelocity(destination, 1);

            registerProjectile(magicProjectile.getProjectile(), castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        damageEntity(castInformation.getMainTarget(), castInformation.getCaster(), getDamage(castInformation));
    }

    @Nonnull
    @Override
    public String getId() {
        return "FAN_OF_ARROWS";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a fan of arrows around you to slice",
            "through your opponents"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SPECTRAL_ARROW;
    }
}
