package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

public class BloodMagics extends Spell {

    public BloodMagics() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 7, false, 25, false)
            .makeDamagingSpell(2, true, 0.5, false)
            .makeTickingSpell(this::cast, 3, false, 20, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final double size = getRange(castInformation);
        final Location location = castInformation.getCastLocation();

        castBlood(castInformation, location, size, 1);
    }

    private void castBlood(CastInformation castInformation, Location location, double size, int iteration) {
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(190, 55, 80), 1F);
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(location, size, size, size);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity
                && entity.getUniqueId() != castInformation.getCaster()
            ) {
                final LivingEntity livingEntity = (LivingEntity) entity;
                GeneralUtils.damageEntity(
                    livingEntity,
                    castInformation.getCaster(),
                    getDamage(castInformation)
                );
                ParticleUtils.displayParticleEffect(livingEntity, 2, 10, dustOptions);
                if (iteration <= 5 && livingEntity.getHealth() <= 0) {
                    GeneralUtils.pushEntity(castInformation.getCaster(), castInformation.getCastLocation(), entity, 5);
                    ParticleUtils.displayParticleEffect(livingEntity, 4, 20, dustOptions);
                    entity.remove();
                    castBlood(castInformation, livingEntity.getLocation(), 2, iteration + 1);
                }
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "BLOOD_MAGICS";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Damages nearby entities. Dying entities will",
            "cause further damage."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.RED_DYE;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HISTORICAL,
            StoryType.PHILOSOPHICAL
        );
    }
}
