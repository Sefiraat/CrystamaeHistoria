package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class EtherealFlow extends Spell {

    public EtherealFlow() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(720, true, 0, false, 10, false)
            .makeTickingSpell(this::onTick, 30, true, 1, false);
        setSpellCore(spellCoreBuilder.build());

    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        location.getWorld().setTime(location.getWorld().getTime() + (50L * castInformation.getStaveLevel()));
        location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
    }

    @Nonnull
    @Override
    public String getId() {
        return "ETHEREAL_FLOW";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Fast-forwards time"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CLOCK;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HISTORICAL,
            StoryType.VOID,
            StoryType.PHILOSOPHICAL
        );
    }
}
