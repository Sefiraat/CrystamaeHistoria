package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FanOfArrows implements CastableProjectile {

    private static final int DAMAGE = 2;
    private static final int COOLDOWN = 5;
    private static final double KNOCK_BACK_FORCE = 0;
    private static final double AOE_RANGE = 0;

    @Override
    public void cast(@Nonnull SpellDefinition spellDefinition) {

        spellDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Player player = spellDefinition.getCaster();
        int sizeEnd = 30;
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

            registerProjectile(magicProjectile.getProjectile(), spellDefinition);
        }

    }

    @Override
    public void affect(@Nonnull SpellDefinition spellDefinition) {
        EntityUtils.damageEntity(spellDefinition.getMainTarget(), spellDefinition.getCaster(), spellDefinition.getDamage());
    }

}
