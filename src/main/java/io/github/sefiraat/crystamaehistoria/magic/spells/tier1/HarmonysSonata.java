package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class HarmonysSonata extends Spell {

    public HarmonysSonata() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 10, true, 10, true)
            .makeTickingSpell(this::onTick, 15, true, 3, false);
        setSpellCore(spellCoreBuilder.build());

    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        final double range = getRange(castInformation);
        Location location = castInformation.getCastLocation().clone().add(
            ThreadLocalRandom.current().nextDouble(-range, range),
            0,
            ThreadLocalRandom.current().nextDouble(-range, range)
        );
        final Block block = location.getBlock();
        if (block.getType() == Material.AIR
            && SlimefunTag.DIRT_VARIANTS.isTagged(block.getRelative(BlockFace.DOWN).getType())
            && GeneralUtils.hasPermission(castInformation.getCaster(), block, Interaction.PLACE_BLOCK)
        ) {
            final Set<Material> set = Tag.FLOWERS.getValues();
            final Material material = set.stream()
                .skip(ThreadLocalRandom.current().nextInt(set.size()))
                .findAny()
                .orElse(Material.DANDELION);
            if (Tag.TALL_FLOWERS.isTagged(material)) {
                final Block upper = block.getRelative(BlockFace.UP);
                if (upper.getType() == Material.AIR) {
                    block.setType(material, false);
                    upper.setType(material, false);
                    final Bisected bisectedTop = (Bisected) upper.getBlockData();
                    bisectedTop.setHalf(Bisected.Half.TOP);
                    upper.setBlockData(bisectedTop);
                    final Bisected bisectedBottom = (Bisected) block.getBlockData();
                    bisectedBottom.setHalf(Bisected.Half.BOTTOM);
                    block.setBlockData(bisectedBottom);
                } else {
                    block.setType(Material.DANDELION);
                }
            } else {
                block.setType(material);
            }
            block.getRelative(BlockFace.DOWN).setType(Material.GRASS_BLOCK);
            ParticleUtils.displayParticleEffect(block.getLocation(), Particle.FIREWORKS_SPARK, 0.5, 3);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.ALCHEMICAL,
            StoryType.CELESTIAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Allows a rich garden to grow at your feet."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "HARMONYS_SONATA";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GRASS;
    }
}
