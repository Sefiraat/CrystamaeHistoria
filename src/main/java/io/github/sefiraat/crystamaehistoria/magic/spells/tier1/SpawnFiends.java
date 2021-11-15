package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.FiendGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SpawnFiends extends Spell {

    public SpawnFiends() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 50, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        for (int i = 0; i < castInformation.getStaveLevel(); i++) {
            Location spawnLocation = location.clone().add(
                ThreadLocalRandom.current().nextDouble(-3, 3),
                0,
                ThreadLocalRandom.current().nextDouble(-3, 3)
            );
            Phantom phantom = (Phantom) SpellUtils.summonTemporaryMob(
                EntityType.PHANTOM,
                caster,
                spawnLocation,
                new FiendGoal(caster),
                180
            );
            phantom.setSize(2);
            phantom.setShouldBurnInDay(false);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "SPAWN_FIENDS";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons 1-5 fiends to attack your enemies.",
            "Fiends are erratic and do not follow the caster."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.PHANTOM_SPAWN_EGG;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ANIMAL,
            StoryType.VOID,
            StoryType.PHILOSOPHICAL
        );
    }

}
