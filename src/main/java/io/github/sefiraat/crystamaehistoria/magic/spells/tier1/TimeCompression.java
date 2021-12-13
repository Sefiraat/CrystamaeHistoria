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

public class TimeCompression extends Spell {

    public TimeCompression() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, false, 50, true)
            .makeTickingSpell(this::cast, 10, true, 20, false)
            .makeEffectingSpell(true, false)
            .addPositiveEffect(PotionEffectType.JUMP, 1, 2)
            .addPositiveEffect(PotionEffectType.SPEED, 1, 2)
            .addPositiveEffect(PotionEffectType.FAST_DIGGING, 1, 2)
            .addPositiveEffect(PotionEffectType.DOLPHINS_GRACE, 1, 2);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(20, 200, 120), 1F);
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
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyPositiveEffects(livingEntity, castInformation);
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
            StoryType.VOID
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
        return "TIME_COMPRESSION";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.WAXED_CUT_COPPER;
    }
}
