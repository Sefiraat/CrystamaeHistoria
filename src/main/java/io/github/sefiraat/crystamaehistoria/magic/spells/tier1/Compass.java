package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class Compass extends Spell {

    public Compass() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, false, 20, true, 50, true)
            .makeTickingSpell(this::onTick, 10, true, 20, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        final Player player = castInformation.getCasterAsPlayer();
        final Location location = player.getLocation().add(0, 1.5, 0);
        final ItemStack itemStack = player.getInventory().getItemInOffHand();
        final Material material = itemStack.getType();
        if (material != Material.AIR && material.isBlock()) {
            final Block foundBlock = tryGetBlock(player, material, (int) getRange(castInformation));
            if (foundBlock == null) {
                ParticleUtils.displayParticleEffect(location.add(location.getDirection()),
                    Particle.VILLAGER_ANGRY,
                    1,
                    10
                );
            } else {
                final Location foundBlockLocation = foundBlock.getLocation().add(0.5, 0.5, 0.5);
                final List<Integer> list = (List<Integer>) CrystamaeHistoria.getConfigManager().getBlockColors().getList(material.name());
                final Color color = Color.fromRGB(list.get(0), list.get(1), list.get(2));
                final Particle.DustOptions dustOptionsToBlock = new Particle.DustOptions(color, 1);
                ParticleUtils.drawLine(dustOptionsToBlock, location, foundBlockLocation, 0.2);
            }
        }
    }

    @Nullable
    public Block tryGetBlock(Player player, Material material, int range) {
        final Location location = player.getLocation();
        for (int x = -range; x < range; x++) {
            for (int y = -range; y < range; y++) {
                for (int z = -range; z < range; z++) {
                    Block block = location.clone().add(x, y, z).getBlock();
                    if (block.getType() == material) {
                        return block;
                    }
                }
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public String getId() {
        return "COMPASS";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Tap into the ether to find blocks.",
            "Will locate blocks matching the one",
            "in the caster's off-hand."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.COMPASS;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ALCHEMICAL,
            StoryType.HUMAN
        );
    }
}
