package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.TimePeriod;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GreenHouseGlass extends TickingBlockNoGui {

    @Getter
    private final int rate;
    @Getter
    private final Map<Location, UUID> blockOwnerMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public GreenHouseGlass(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int rate) {
        super(category, item, recipeType, recipe);
        this.rate = rate;
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        blockOwnerMap.put(
            block.getLocation(),
            UUID.fromString(BlockStorage.getLocationInfo(block.getLocation(), "CH_UUID"))
        );
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        if (!GeneralUtils.testChance(this.rate, 100) || TimePeriod.isLight(block.getWorld())) {
            return;
        }

        final BlockFace direction = BlockFace.DOWN;
        final Location location = block.getLocation().subtract(0, 1, 0);

        RayTraceResult result = location.getWorld().rayTrace(
            location,
            direction.getDirection(),
            30,
            FluidCollisionMode.ALWAYS,
            false,
            0.1,
            null
        );

        if (result == null) {
            return;
        }

        final Block testBlock = result.getHitBlock();
        if (testBlock == null || testBlock.isSolid()) {
            return;
        }

        final BlockData blockData = testBlock.getBlockData();
        if (blockData instanceof Ageable) {
            Ageable ageable = (Ageable) blockData;
            if (ageable.getAge() < ageable.getMaximumAge()) {
                ageable.setAge(ageable.getAge() + 1);
                ParticleUtils.displayParticleEffect(testBlock.getLocation().add(.5, .5, .5), Particle.SPELL_WITCH, 0.5, 2);
                testBlock.setBlockData(ageable);
            }
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        BlockStorage.addBlockInfo(event.getBlock(), "CH_UUID", uuid.toString());
        blockOwnerMap.put(event.getBlock().getLocation(), uuid);
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
        // No on break
    }
}
