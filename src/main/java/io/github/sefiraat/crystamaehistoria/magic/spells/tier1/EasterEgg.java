package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EasterEgg extends Spell {

    public EasterEgg() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(600, false, 5, false, 1, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation().add(0, 1, 0);
        for (String string : ThemeType.getEggNames()) {
            final List<Material> list = CrystaTag.SPAWN_EGGS.getValues().stream().toList();
            final int randomValue = ThreadLocalRandom.current().nextInt(0, list.size());
            DisplayItem displayItem = new DisplayItem(
                new ItemStack(list.get(randomValue)),
                location,
                string,
                item -> {
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 1);
                    ParticleUtils.displayParticleEffect(item, 0.3, 4, dustOptions);
                }
            );
            displayItem.registerRemoval(5000);
            displayItem.setVelocity(new Vector(
                ThreadLocalRandom.current().nextDouble(-.5, .6),
                .5,
                ThreadLocalRandom.current().nextDouble(-.5, .6)
            ));
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ANIMAL,
            StoryType.CELESTIAL,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "???"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "EASTER_EGG";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.ZOGLIN_SPAWN_EGG;
    }
}
