package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PhilosophersStone extends Spell {

    protected static final List<Material> MATERIALS_RARE = new ArrayList<>();
    protected static final List<Material> MATERIALS_UNCOMMON = new ArrayList<>();
    protected static final List<Material> MATERIALS_COMMON = new ArrayList<>();

    static {
        MATERIALS_RARE.add(Material.COPPER_BLOCK);
        MATERIALS_RARE.add(Material.IRON_BLOCK);
        MATERIALS_RARE.add(Material.EMERALD_BLOCK);
        MATERIALS_RARE.add(Material.DIAMOND_BLOCK);
        MATERIALS_RARE.add(Material.GOLD_BLOCK);
        MATERIALS_UNCOMMON.addAll(SlimefunTag.ORES.getValues());
        MATERIALS_UNCOMMON.addAll(CrystaTag.CONCRETE_BLOCKS.getValues());
        MATERIALS_UNCOMMON.addAll(CrystaTag.GLAZED_TERRACOTTA.getValues());
        MATERIALS_COMMON.addAll(Tag.STONE_ORE_REPLACEABLES.getValues());
        MATERIALS_COMMON.addAll(Tag.DIRT.getValues());
        MATERIALS_COMMON.addAll(Tag.LOGS.getValues());
        MATERIALS_COMMON.addAll(Tag.SAND.getValues());
        MATERIALS_COMMON.addAll(Tag.SNOW.getValues());
        MATERIALS_COMMON.addAll(SlimefunTag.CONCRETE_POWDERS.getValues());
    }

    public PhilosophersStone() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 50, true, 5, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Player player = castInformation.getCasterAsPlayer();
        final Block block = player.getTargetBlockExact((int) getRange(castInformation));
        if (GeneralUtils.hasPermission(player, block, Interaction.BREAK_BLOCK)
            && block.getType().getHardness() != -1
            && !block.getType().isAir()
        ) {
            final Location location = block.getLocation();
            final int random = ThreadLocalRandom.current().nextInt(100);
            Material material;
            if (random > 95) {
                material = MATERIALS_RARE.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_RARE.size()));
            } else if (random > 60) {
                material = MATERIALS_UNCOMMON.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_UNCOMMON.size()));
            } else {
                material = MATERIALS_COMMON.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_COMMON.size()));
            }
            final List<Integer> list = (List<Integer>) CrystamaeHistoria.getConfigManager().getBlockColors().getList(material.name());
            final Color color = Color.fromRGB(list.get(0), list.get(1), list.get(2));
            final Particle.DustOptions dustOptionsToBlock = new Particle.DustOptions(color, 1);
            ParticleUtils.drawCube(dustOptionsToBlock, location, location.clone().add(1, 1, 1), 0.25);
            block.setType(material);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HUMAN,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Converts the targets block into...",
            "something else..."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "PHILOSOPHERS_STONE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GOLD_BLOCK;
    }
}
