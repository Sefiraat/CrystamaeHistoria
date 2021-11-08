package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobCandle extends TickingBlockNoGui {

    @Getter
    private final double radius;
    @Getter
    private final int duration;
    @Getter
    private final Map<Location, Long> expiryMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public MobCandle(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int radius, int durationInSeconds) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
        this.duration = durationInSeconds;
    }

    @Override
    protected void onFirstTick(@NotNull Block block, @NotNull SlimefunItem slimefunItem, @NotNull Config config) {
        Long expiry = Long.valueOf(BlockStorage.getLocationInfo(block.getLocation(), "EXPIRY"));
        expiryMap.put(block.getLocation(), expiry);
    }

    @Override
    protected void onTick(@NotNull Block block, @NotNull SlimefunItem slimefunItem, @NotNull Config config) {
        Location location = block.getLocation();
        BoundingBox boundingBox = new BoundingBox(
            location.getX() - radius,
            location.getY() - radius,
            location.getZ() - radius,
            location.getX() + radius,
            location.getY() + radius,
            location.getZ() + radius
        );
        CrystamaeHistoria.getSpellMemory().getNoSpawningAreas().put(boundingBox, System.currentTimeMillis() + 2000);
        Long expiry = expiryMap.get(block.getLocation());
        if (expiry < System.currentTimeMillis()) {
            BlockStorage.clearBlockInfo(block);
            block.setType(Material.AIR);
            ParticleUtils.displayParticleEffect(
                block.getLocation().add(0.5, 0.5, 0.5),
                Particle.CAMPFIRE_SIGNAL_SMOKE,
                0.5,
                3
            );
        }
    }

    @Override
    protected void onPlace(@NotNull BlockPlaceEvent event) {
        Long expiry = System.currentTimeMillis() + (duration * 1000L);
        BlockStorage.addBlockInfo(event.getBlock(), "EXPIRY", String.valueOf(expiry));
        expiryMap.put(event.getBlock().getLocation(), expiry);
    }

    @Override
    protected void onBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
        BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
    }
}
