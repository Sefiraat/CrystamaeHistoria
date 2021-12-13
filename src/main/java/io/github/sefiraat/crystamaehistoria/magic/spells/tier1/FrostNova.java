package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class FrostNova extends Spell {

    public FrostNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, false, 10, false)
            .makeDamagingSpell(2, true, 1, false)
            .makeProjectileSpell(this::fireProjectiles, 2, false, 1, false)
            .makeProjectileVsEntitySpell(this::projectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        double sizeEnd = getRange(castInformation);
        int sizeCast = 2;
        int stepSize = 3;
        Location middle = castInformation.getCastLocation().clone().add(0, 1, 0);
        for (double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            int dx = (int) (sizeEnd * Math.cos(angle));
            int dz = (int) (sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 0, dz);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SNOWBALL, spawn);
            magicProjectile.setVelocity(destination, 1);
        }

    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), getDamage(castInformation));
            livingEntity.setFreezeTicks(60);
            ParticleUtils.displayParticleEffect(livingEntity, Particle.SNOW_SHOVEL, 1, 5);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.MECHANICAL,
            StoryType.VOID
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a massive nova of snowballs",
            "around you to freeze foes."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "FROST_NOVA";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SNOWBALL;
    }
}
