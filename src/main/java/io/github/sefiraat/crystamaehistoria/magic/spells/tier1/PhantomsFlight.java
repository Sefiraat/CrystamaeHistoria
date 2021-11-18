package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.FlyingBatGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PhantomsFlight extends Spell {

    public PhantomsFlight() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 50, true)
            .makeInstantSpell(this::cast);
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
            EntityType.BAT,
            caster,
            spawnLocation,
            new FlyingBatGoal(caster),
            castInformation.getStaveLevel() * 300,
            this::onTick
        );
        Bat bat = (Bat) magicSummon.getMob();
        bat.setInvisible(true);
        bat.setInvulnerable(true);
        bat.addPassenger(castInformation.getCasterAsPlayer());
    }

    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect(magicSummon.getMob(), Particle.SPORE_BLOSSOM_AIR, 1, 2);
    }

    @Nonnull
    @Override
    public String getId() {
        return "PHANTOMS_FLIGHT";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a dragon to ride.",
            "Getting off the dragon will make",
            "it fly away."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.DRAGON_EGG;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ANIMAL,
            StoryType.CELESTIAL,
            StoryType.PHILOSOPHICAL
        );
    }

}
