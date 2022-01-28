package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

public class StarFall extends Spell {

    public StarFall() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 7, true, 40, true)
            .makeDamagingSpell(2, true, 0.5, false)
            .makeProjectileSpell(this::fireProjectiles, 1, true, 0, false)
            .makeProjectileVsEntitySpell(this::projectileHits)
            .makeProjectileVsBlockSpell(this::projectileHits)
            .makeTickingSpell(this::fireProjectiles, 9, false, 10, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        final Location location = castInformation.getCastLocation();
        final double size = getRange(castInformation);
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(location, size, size, size);

        for (Entity entity : entities) {
            if (GeneralUtils.testChance(1, 5)
                && entity instanceof LivingEntity
                && entity.getUniqueId() != castInformation.getCaster()
            ) {
                final Location spawnLocation = entity.getLocation().clone().add(0, 100, 0);
                final Location destination = spawnLocation.clone().subtract(0, 100, 0);
                final MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.TRIDENT, spawnLocation, this::onTick);

                magicProjectile.setVelocity(destination, 2);
                magicProjectile.disableGravity();
            }
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHits(CastInformation castInformation) {
        for (final LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(
                livingEntity,
                castInformation.getCaster(),
                getDamage(castInformation)
            );
        }
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicProjectile magicProjectile) {
        final Location location = magicProjectile.getProjectile().getLocation();
        final Particle.DustOptions dustOptions = new Particle.DustOptions(
            Color.fromRGB(135, 50, 235),
            2F
        );
        location.getWorld().spawnParticle(
            Particle.REDSTONE,
            location,
            10,
            dustOptions
        );
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.CELESTIAL,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Rains celestial beings from the skies",
            "to decimate your opponents."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "STAR_FALL";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.NETHER_STAR;
    }
}
