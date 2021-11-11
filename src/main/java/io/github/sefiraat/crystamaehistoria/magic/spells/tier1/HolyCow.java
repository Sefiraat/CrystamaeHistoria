package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.HolyCowGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class HolyCow extends Spell {

    public HolyCow() {
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
            SpellUtils.summonTemporaryMob(
                EntityType.COW,
                caster,
                spawnLocation,
                new HolyCowGoal(caster),
                300,
                this::onTick
            );
        }
    }

    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect(magicSummon.getMob(), Particle.GLOW, 1, 4);
    }

    @Nonnull
    @Override
    public String getId() {
        return "HOLY_COW";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons 1-5 cows to who will protect you",
            "with their lives."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.COW_SPAWN_EGG;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ANIMAL,
            StoryType.CELESTIAL,
            StoryType.VOID
        );
    }

}
