package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.DeityGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Deity extends Spell {

    public Deity() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(600, true, 0, false, 1000, true)
            .makeInstantSpell(this::cast)
            .addPositiveEffect(PotionEffectType.GLOWING, 1, 120);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final UUID caster = castInformation.getCaster();
        final Location location = castInformation.getCastLocation();
        final Location spawnLocation = location.clone().add(
            ThreadLocalRandom.current().nextDouble(-3, 3),
            0,
            ThreadLocalRandom.current().nextDouble(-3, 3)
        );
        final MagicSummon magicSummon = SpellUtils.summonTemporaryMob(
            EntityType.GIANT,
            caster,
            spawnLocation,
            new DeityGoal(caster),
            120
        );
        applyPositiveEffects(magicSummon.getMob(), castInformation);
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.ANIMAL,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a deity to your side. It",
            "does... Nothing!"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "DEITY";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.END_CRYSTAL;
    }

}
