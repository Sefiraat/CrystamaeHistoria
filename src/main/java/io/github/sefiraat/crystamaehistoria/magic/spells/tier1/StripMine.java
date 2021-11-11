package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class StripMine extends Spell {

    public StripMine() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 10, true, 25, true)
            .makeTickingSpell(this::tick, 10, true, 5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {

        BlockFace blockFace = castInformation.getTargetedBlockFaceOnCast();

        if (blockFace == BlockFace.UP || blockFace == BlockFace.DOWN) {
            return;
        }

        int xOffset = 0;
        int zOffset = 0;
        Vector faceDirection = castInformation.getTargetedBlockFaceOnCast().getDirection();
        if (faceDirection.getX() == 0) {
            zOffset = (int) ((castInformation.getCurrentTick() - 1) * -faceDirection.getZ());
        } else if (faceDirection.getZ() == 0) {
            xOffset = (int) ((castInformation.getCurrentTick() - 1) * -faceDirection.getX());
        }

        final Block block = castInformation.getTargetedBlockOnCast().getRelative(xOffset, 0, zOffset);
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        if (GeneralUtils.tryBreakBlock(castInformation.getCaster(), block)
            && GeneralUtils.tryBreakBlock(castInformation.getCaster(), blockBelow)
            && castInformation.getCurrentTick() % 5 == 0
        ) {
            blockBelow.setType(Material.TORCH);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "STRIP_MINE";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Magically strips a mineshaft starting from",
            "the targeted block."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.MINECART;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ALCHEMICAL,
            StoryType.HISTORICAL
        );
    }
}
