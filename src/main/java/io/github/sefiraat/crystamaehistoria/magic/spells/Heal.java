package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.SpellRecipe;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        healEntity(caster, getHealAmount(castInformation));
        displayParticleEffect(caster, Particle.HEART, 2, 10);
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

    @NotNull
    @Override
    public SpellRecipe getRecipe() {
        return new SpellRecipe(
            Materials.INERT_PLATE_T_1,
            StoryType.HUMAN,
            StoryType.CELESTIAL,
            StoryType.PHILOSOPHICAL
        );
    }
}
