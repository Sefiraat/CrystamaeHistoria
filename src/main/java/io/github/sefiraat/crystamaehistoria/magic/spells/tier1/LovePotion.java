package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class LovePotion extends Spell {

    public LovePotion() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 5, true, 50, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range, Breedable.class::isInstance)) {
            Animals animals = (Animals) entity;
            if (animals.isAdult() && animals.canBreed()) {
                animals.setLoveModeTicks(120);
                ParticleUtils.displayParticleEffect(entity, Particle.HEART, 1, 5);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "LOVE_POTION";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "All nearby breedable entities get... friendly"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.POTION;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.ANIMAL,
            StoryType.CELESTIAL
        );
    }
}
