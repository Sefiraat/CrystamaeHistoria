package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TimeDilation extends Spell {

    public TimeDilation() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, false, 50, true)
            .makeTickingSpell(this::cast, 10, true, 20, false)
            .makeEffectingSpell(true, false)
            .addNegativeEffect(PotionEffectType.JUMP, -2, 2)
            .addNegativeEffect(PotionEffectType.SLOW_FALLING, 2, 2)
            .addNegativeEffect(PotionEffectType.SLOW, 2, 2)
            .addNegativeEffect(PotionEffectType.SLOW_DIGGING, 2, 2);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(120, 20, 200), 1F);
        final Location location = castInformation.getCastLocation().clone().add(0, 1, 0);
        final double range = getRange(castInformation);
        final double effectRange = range * 0.75;
        final int density = 30;
        // Particles
        for (double height = 0; height <= Math.PI; height += Math.PI / density) {
            final double r = range * Math.sin(height);
            final double y = range * Math.cos(height);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / density) {
                final double x = Math.cos(a) * r;
                final double z = Math.sin(a) * r;
                final Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, 0.1, 1, dustOptions);
            }
        }
        // Effects
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity, castInformation);
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HISTORICAL,
            StoryType.ANIMAL,
            StoryType.CELESTIAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Makes all things around the caster",
            "shift into a different time-space."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "TIME_DILATION";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CUT_COPPER;
    }
}
