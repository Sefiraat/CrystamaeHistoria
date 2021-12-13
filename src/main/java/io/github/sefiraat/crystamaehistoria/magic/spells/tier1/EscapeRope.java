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
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class EscapeRope extends Spell {

    public EscapeRope() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(3600, true, 0, false, 30, false)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        Location location = player.getLocation();
        Location teleportTo = location.getWorld().getHighestBlockAt(location).getLocation().add(0, 2, 0);
        player.teleportAsync(teleportTo);
        ParticleUtils.displayParticleEffect(location, Particle.NOTE, 1, 5);
        ParticleUtils.displayParticleEffect(teleportTo, Particle.NOTE, 1, 5);
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HISTORICAL,
            StoryType.CELESTIAL,
            StoryType.VOID
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Teleports you to the highest possible",
            "point if possible."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "ESCAPE_ROPE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.LEAD;
    }
}
