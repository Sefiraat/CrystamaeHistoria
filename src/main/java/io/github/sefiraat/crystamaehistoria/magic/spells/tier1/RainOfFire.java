package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class RainOfFire extends Spell {

    private static final int PROJECTILES_PER_WAVE = 5;

    public RainOfFire() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 20, false, 20, true)
            .makeDamagingSpell(5, true, 0.5, false)
            .makeProjectileSpell(this::fireProjectiles, 1, true, 0.5, true)
            .makeProjectileVsEntitySpell(this::projectileHits)
            .addBeforeProjectileHitEntityEvent(this::beforeProjectileHits)
            .makeTickingSpell(this::fireProjectiles, 9, false, 10, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();

        for (int i = 0; i < (PROJECTILES_PER_WAVE * castInformation.getStaveLevel()); i++) {

            double range = getRange(castInformation);

            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1);
            Location spawnLocation = new Location(
                location.getWorld(),
                location.getX() + xOffset,
                location.getY() + 20,
                location.getZ() + zOffset
            );

            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.FIREBALL, spawnLocation);
            Location destination = spawnLocation.clone().subtract(0, 20, 0);
            magicProjectile.setVelocity(destination, 2);
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHits(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(80);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHits(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity,
                castInformation.getCaster(),
                getDamage(castInformation),
                castInformation.getDamageLocation(),
                getProjectileKnockback(castInformation)
            );
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "RAIN_OF_FIRE";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons an epic hellscape of raining",
            "fire."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.HUMAN,
            StoryType.VOID
        );
    }
}
