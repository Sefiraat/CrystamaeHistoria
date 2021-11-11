package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlutosDecent extends Spell {

    protected static final List<Material> MATERIALS = new ArrayList<>();

    static {
        MATERIALS.add(Material.BLACKSTONE_SLAB);
        MATERIALS.add(Material.BLACKSTONE_STAIRS);
        MATERIALS.add(Material.BLACKSTONE_WALL);
        MATERIALS.add(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    }

    public PlutosDecent() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(25, false, 60, false, 75, true)
            .makeDamagingSpell(5, true, 1, false)
            .makeProjectileSpell(this::cast, 0.5, true, 1, true)
            .makeProjectileVsBlockSpell(this::blockLands);
        setSpellCore(spellCoreBuilder.build());
    }

    private void cast(CastInformation castInformation) {
        final Player player = castInformation.getCasterAsPlayer();
        final int range = (int) getRange(castInformation);
        final Block targetBlock = player.getTargetBlockExact(range);

        if (targetBlock != null) {
            final Location target = targetBlock.getLocation().add(0.5, 0.5, 0.5);
            final List<Block> blocks = new ArrayList<>();
            final int radius = getRadius(castInformation);

            for (int y = -radius; y < radius; y++) {
                for (int x = -radius; x < radius; x++) {
                    for (int z = -radius; z < radius; z++) {
                        if (Math.sqrt((double) (x * x) + (y * y) + (z * z)) <= range) {
                            final Block block = target.getWorld().getBlockAt(
                                x + target.getBlockX(),
                                y + target.getBlockY(),
                                z + target.getBlockZ());
                            if (!blocks.contains(block)
                                && GeneralUtils.hasPermission(player, block, Interaction.PLACE_BLOCK)
                            ) {
                                blocks.add(block);
                            }
                        }
                    }
                }
            }
            spawnBlocks(castInformation, blocks);
        }
    }

    private void spawnBlocks(CastInformation castInformation, List<Block> blocks) {
        for (Block block : blocks) {
            MagicFallingBlock magicFallingBlock = SpellUtils.summonMagicFallingBlock(
                castInformation,
                block.getLocation().add(0, 40, 0),
                MATERIALS.get(ThreadLocalRandom.current().nextInt(MATERIALS.size())),
                5
            );
            magicFallingBlock.setVelocity(block.getLocation(), 2);
        }
    }

    private int getRadius(CastInformation castInformation) {
        final int radius;

        switch (castInformation.getStaveLevel()) {
            case 3:
            case 4:
                radius = 2;
                break;
            case 5:
                radius = 3;
                break;
            default:
                radius = 1;
                break;
        }
        return radius;
    }

    private void blockLands(CastInformation castInformation) {
        Location location = castInformation.getHitBlock().getLocation();
        location.getWorld().createExplosion(
            castInformation.getCasterAsPlayer(),
            location,
            getRadius(castInformation) + 1F,
            true,
            true
        );
    }

    @Nonnull
    @Override
    public String getId() {
        return "PLUTOS_DESCENT";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a meteor to strike down your foes."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.COBBLED_DEEPSLATE;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.ALCHEMICAL,
            StoryType.HUMAN
        );
    }
}
