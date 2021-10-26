package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.SpellRecipe;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Break extends Spell {

    public Break() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 50, true, 5, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        Block block = caster.getTargetBlockExact((int) getRange(castInformation));
        if (hasPermission(caster, block.getLocation(), Interaction.BREAK_BLOCK)) {
            block.breakNaturally(caster.getInventory().getItemInMainHand());
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "BREAK";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Breaks the block being targeted"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CRACKED_STONE_BRICKS;
    }

    @NotNull
    @Override
    public SpellRecipe getRecipe() {
        return new SpellRecipe(
            1,
            StoryType.ELEMENTAL,
            StoryType.MECHANICAL,
            StoryType.ALCHEMICAL
        );
    }
}
