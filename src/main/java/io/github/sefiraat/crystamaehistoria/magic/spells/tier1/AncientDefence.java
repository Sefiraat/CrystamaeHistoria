package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class AncientDefence extends Spell {

    public AncientDefence() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 5, false, 25, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Location location = castInformation.getCastLocation();
        final double range = getRange(castInformation);
        final int density = 50;
        for (double height = 0; height <= Math.PI; height += Math.PI / density) {
            final double r = range * Math.sin(height);
            final double y = range * Math.cos(height);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / density) {
                final double x = Math.cos(a) * r;
                final double z = Math.sin(a) * r;
                final Block block = location.clone().add(x, y, z).getBlock();
                if (block.getType() == Material.AIR
                    && GeneralUtils.hasPermission(castInformation.getCaster(), block, Interaction.PLACE_BLOCK)
                ) {
                    block.setType(Material.RED_STAINED_GLASS);
                    GeneralUtils.markBlockForRemoval(block, 5);
                }
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.HISTORICAL,
            StoryType.HUMAN
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a defensive wall in front of",
            "the caster."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "ANCIENT_DEFENCE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CRYING_OBSIDIAN;
    }
}
