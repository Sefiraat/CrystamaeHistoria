package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Gyroscopic extends Spell {

    public Gyroscopic() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 15, false, 50, true)
            .makeTickingSpell(this::cast, 20, true, 5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0, 1, 0);
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
                ParticleUtils.displayParticleEffect(point, Particle.GLOW, 0.1, 1);
            }
        }
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (entity instanceof LivingEntity
                && GeneralUtils.hasPermission(castInformation.getCaster(), entity.getLocation(), Interaction.INTERACT_ENTITY)
                && entity.getUniqueId() != castInformation.getCaster()
            ) {
                Location newLocation = entity.getLocation().clone();
                newLocation.setYaw(entity.getLocation().getYaw() + 10F);
                entity.teleport(newLocation);
                ParticleUtils.displayParticleEffect(entity, Particle.SPELL, 1, 1);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "GYROSCOPIC";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "You spin me right round baby..."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.MUSIC_DISC_CAT;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ANIMAL,
            StoryType.CELESTIAL
        );
    }
}
