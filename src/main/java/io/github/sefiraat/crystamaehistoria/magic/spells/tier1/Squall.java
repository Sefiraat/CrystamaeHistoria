package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Squall extends Spell {

    public Squall() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(2000, true, 0, false, 10, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        if (caster != null) {
            caster.getWorld().setThundering(true);
            caster.getWorld().setStorm(true);
            ParticleUtils.displayParticleEffect(caster, Particle.ELECTRIC_SPARK, 2, 30);
            caster.getWorld().playEffect(caster.getLocation(), Effect.BONE_MEAL_USE, 1);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HISTORICAL,
            StoryType.VOID
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Causes the heavens to open up and pour",
            "down."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "SQUALL";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.BUCKET;
    }
}
