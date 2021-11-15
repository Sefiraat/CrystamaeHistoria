package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Heal extends Spell {

    public Heal() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 0, false, 1, true)
            .makeInstantSpell(this::cast)
            .makeHealingSpell(2, true);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        GeneralUtils.healEntity(caster, getHealAmount(castInformation));
        ParticleUtils.displayParticleEffect(caster, Particle.HEART, 2, 10);
    }

    @Nonnull
    @Override
    public String getId() {
        return "HEAL";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Heals the caster for a set amount of HP"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.POTION;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HUMAN,
            StoryType.CELESTIAL,
            StoryType.PHILOSOPHICAL
        );
    }
}
