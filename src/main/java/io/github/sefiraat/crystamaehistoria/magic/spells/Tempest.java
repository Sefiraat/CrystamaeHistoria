package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class Tempest extends Spell {

    private static final double PROJECTILE_NUMBER = 10;

    public Tempest() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(200, true, 20, false, 10, true)
                .makeDamagingSpell(2, true, 0, false)
                .makeProjectileSpell(this::fireProjectiles, this::onProjectileHit, 2, false, 2, false)
                .addBeforeProjectileHitEvent(this::beforeProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectiles(@NonNull SpellCastInformation spellCastInformation) {
        Location location = spellCastInformation.getCastLocation();

        for (int i = 0; i < (PROJECTILE_NUMBER * spellCastInformation.getPowerMulti()); i++) {
            double range = getRange(spellCastInformation);
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
            registerProjectile(lightningStrike, spellCastInformation);
        }
    }

    public void beforeProjectileHit(@NonNull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)){
            livingEntity.setFireTicks(40);
        }
    }

    public void onProjectileHit(@NonNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)) {
            damageEntity(livingEntity, spellCastInformation.getCaster(), getDamage(spellCastInformation), spellCastInformation.getDamageLocation(), getKnockback(spellCastInformation));
        }
    }

}
