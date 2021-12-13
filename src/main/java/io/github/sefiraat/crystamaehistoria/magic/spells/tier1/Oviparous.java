package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Turtle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Oviparous extends Spell {

    public Oviparous() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, true, 5, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range)) {
            Location location = entity.getLocation();
            if (entity instanceof Chicken) {
                ItemStack itemStack = new ItemStack(Material.EGG);
                location.getWorld().dropItemNaturally(location, itemStack);
                Particle.DustOptions dustOptions = new Particle.DustOptions(
                    Color.fromRGB(190, 170, 115),
                    2F
                );
                ParticleUtils.displayParticleEffect(location, 1, 5, dustOptions);
                entity.setVelocity(new Vector(0, 1, 0));
            }
            if (entity instanceof Turtle) {
                Block block = location.clone().add(0, 0.5, 0).getBlock();
                if (block.getType() == Material.AIR
                    && block.getRelative(BlockFace.DOWN).getType().isSolid()
                ) {
                    block.setType(Material.TURTLE_EGG);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(
                        Color.fromRGB(95, 160, 80),
                        2F
                    );
                    ParticleUtils.displayParticleEffect(location, 1, 5, dustOptions);
                    entity.setVelocity(new Vector(0, 0.5, 0));
                }
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ANIMAL,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Give me dem eggs!"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "OVIPAROUS";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.EGG;
    }
}
