package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class Tempest extends Spell {

    private static final double PROJECTILE_NUMBER = 5;

    public Tempest() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(200, true, 20, false, 10, true)
            .makeDamagingSpell(2, true, 0, false)
            .makeProjectileSpell(this::fireProjectiles, this::onProjectileHit, 2, false, 2, false)
            .addBeforeProjectileHitEvent(this::beforeProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();

        for (int i = 0; i < (PROJECTILE_NUMBER * castInformation.getStaveLevel()); i++) {
            double range = getRange(castInformation);
            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double x = location.getX() + xOffset;
            double z = location.getZ() + zOffset;
            Location spawnLocation = new Location(
                location.getWorld(),
                location.getX() + xOffset,
                location.getWorld().getHighestBlockYAt((int) x, (int) z),
                location.getZ() + zOffset
            );

            LightningStrike lightningStrike = spawnLocation.getWorld().strikeLightning(spawnLocation);
            registerProjectile(lightningStrike, castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(40);
        }
    }

    @ParametersAreNonnullByDefault
    public void onProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            damageEntity(livingEntity, castInformation.getCaster(), getDamage(castInformation), castInformation.getDamageLocation(), getKnockback(castInformation));
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "TEMPEST";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a tempest of lightning around the",
            "caster causing damage and knockback."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.END_ROD;
    }
}
