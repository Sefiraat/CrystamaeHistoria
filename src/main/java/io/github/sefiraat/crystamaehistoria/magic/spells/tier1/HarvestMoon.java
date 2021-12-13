package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class HarvestMoon extends Spell {

    public HarvestMoon() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 5, true, 50, true)
            .makeTickingSpell(this::cast, 20, true, 10, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0, 1, 0);
        final double range = getRange(castInformation);
        final int density = (int) (3 * range);
        // Particles
        for (double height = 0; height <= Math.PI; height += Math.PI / density) {
            final double r = range * Math.sin(height);
            final double y = range * Math.cos(height);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / density) {
                final double x = Math.cos(a) * r;
                final double z = Math.sin(a) * r;
                final Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.WAX_OFF, 0.1, 1);
            }
        }

        // Bonemealing
        for (int i = 0; i < castInformation.getStaveLevel() * 3; i++) {
            final int x = ThreadLocalRandom.current().nextInt((int) -range, (int) range);
            final int z = ThreadLocalRandom.current().nextInt((int) -range, (int) range);
            final Block potentialBlock = location.add(x, 0, z).getBlock();
            if (Tag.CROPS.isTagged(potentialBlock.getType())) {
                potentialBlock.applyBoneMeal(BlockFace.UP);
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.HUMAN,
            StoryType.ANIMAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Speaks to the spirit of nature",
            "to speed up the growth of nearby",
            "crops. Crops must have direct access",
            "to the sun."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "HARVEST_MOON";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.WHEAT;
    }
}
