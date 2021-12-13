package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Protectorate extends Spell {

    public Protectorate() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 7, false, 50, true)
            .makeTickingSpell(this::cast, 10, true, 20, false);
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
                ParticleUtils.displayParticleEffect(point, Particle.ELECTRIC_SPARK, 0.1, 1);
            }
        }
        // Effects
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (entity instanceof LivingEntity) {
                PersistentDataAPI.setLong(entity, Keys.PDC_IS_INVULNERABLE, System.currentTimeMillis() + 1050);
                ParticleUtils.displayParticleEffect(entity, Particle.VILLAGER_HAPPY, 1, 3);
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HISTORICAL,
            StoryType.CELESTIAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a shield that follows the caster",
            "preventing damage to those inside."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "PROTECTORATE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SHIELD;
    }
}
