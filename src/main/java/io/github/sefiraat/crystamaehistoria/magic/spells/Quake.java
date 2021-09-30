package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingTickingSpell;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

public class Quake extends AbstractDamagingTickingSpell {

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        registerTicker(spellCastInformation, getTickInterval(), getNumberTicks());
    }

    @Override
    protected int getNumberTicks() {
        return 5;
    }

    @Override
    protected int getTickInterval() {
        return 20;
    }

    @Override
    protected int getBaseCooldown() {
        return 100;
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
    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        Location castLocation = spellCastInformation.getCastLocation().clone();

        for (int i = 0; i < 30; i++) {

            int range = (int) getRange();

            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);

            double directionalXOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);
            double directionalZOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);

            Location spawnLocation = new Location(
                    castLocation.getWorld(),
                    castLocation.getX() + xOffset,
                    castLocation.getY(),
                    castLocation.getZ() + zOffset
            );

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(90,100,105), 2);
            castLocation.getWorld().spawnParticle(Particle.REDSTONE, spawnLocation, 1, directionalXOffset, 2, directionalZOffset, dustOptions);
        }
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity != spellCastInformation.getCaster()) {
                EntityUtils.damageEntity(((LivingEntity) entity), spellCastInformation.getCaster(), spellCastInformation.getDamage());
            }
        }
    }

    @Override
    public void afterAllTicks(@NonNull SpellCastInformation spellCastInformation) {

    }

}
