package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Launch extends Spell {

    public Launch() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, false, 5, true, 30, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        if (player != null) {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(getRange(castInformation)));
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "LAUNCH";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Launches the caster in the facing direction.",
            "Offers no protection!"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.BRAIN_CORAL;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.CELESTIAL,
            StoryType.VOID
        );
    }
}
