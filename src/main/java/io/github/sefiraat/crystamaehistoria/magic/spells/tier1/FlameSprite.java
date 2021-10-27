package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import com.destroystokyo.paper.entity.ai.MobGoals;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.SpellRecipe;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BlazeGoal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FlameSprite extends Spell {

    public FlameSprite() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 1, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        for (int i = 0; i < castInformation.getStaveLevel(); i++) {
            Location spawnLocation = location.clone().add(
                ThreadLocalRandom.current().nextDouble(-3,3),
                0,
                ThreadLocalRandom.current().nextDouble(-3,3)
            );
            SpellUtils.summonTemporaryMob(
                EntityType.BLAZE,
                caster,
                spawnLocation,
                new BlazeGoal(caster)
            );

        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "FLAME_SPRITE";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons 1-5 blazes to attack your enemies."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.WITHER_SKELETON_SKULL;
    }

    @NotNull
    @Override
    public SpellRecipe getRecipe() {
        return new SpellRecipe(
            1,
            StoryType.HUMAN,
            StoryType.ANIMAL,
            StoryType.VOID
        );
    }

}
