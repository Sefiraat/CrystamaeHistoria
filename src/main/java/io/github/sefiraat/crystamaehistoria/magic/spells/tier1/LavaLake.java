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

public class LavaLake extends Spell {

    public LavaLake() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 4, true, 50, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Location location = castInformation.getCastLocation();
        final double size = getRange(castInformation);
        final Block standingBlock = location.getBlock();

        if (GeneralUtils.hasPermission(castInformation.getCaster(), standingBlock, Interaction.PLACE_BLOCK)
            && standingBlock.getType() == Material.AIR
        ) {
            standingBlock.setType(Material.NETHER_BRICKS);
            GeneralUtils.markBlockForRemoval(standingBlock, 6);
            castInformation.getCasterAsPlayer().teleport(location.clone().add(0, 1, 0));
        }

        for (double x = -size; x < size; x++) {
            for (double z = -size; z < size; z++) {
                final Block potentialLava = location.clone().add(x, 0, z).getBlock();
                if (GeneralUtils.hasPermission(castInformation.getCaster(), potentialLava, Interaction.PLACE_BLOCK)
                    && potentialLava.getType() == Material.AIR
                ) {
                    potentialLava.setType(Material.LAVA);
                    GeneralUtils.markBlockForRemoval(potentialLava, 5);
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
            StoryType.ALCHEMICAL,
            StoryType.HISTORICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Spawns a lake of lava around the caster"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "LAVA_LAKE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.LAVA_BUCKET;
    }
}
