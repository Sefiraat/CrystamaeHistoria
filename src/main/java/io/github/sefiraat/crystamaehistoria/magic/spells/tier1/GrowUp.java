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
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class GrowUp extends Spell {

    public GrowUp() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(360, false, 5, true, 120, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range)) {
            if (entity instanceof Ageable) {
                Ageable ageable = (Ageable) entity;
                if (!ageable.isAdult()) {
                    ageable.setAdult();
                    ParticleUtils.displayParticleEffect(entity, Particle.SCRAPE, 1, 3);
                }
            } else if (entity instanceof Slime) {
                Slime slime = (Slime) entity;
                slime.setSize(slime.getSize() + 1);
            } else if (entity instanceof Phantom) {
                Phantom phantom = (Phantom) entity;
                phantom.setSize(phantom.getSize() + 1);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "GROW_UP";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Makes things grow up, and not just babies!"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SLIME_BLOCK;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ANIMAL,
            StoryType.VOID
        );
    }
}
