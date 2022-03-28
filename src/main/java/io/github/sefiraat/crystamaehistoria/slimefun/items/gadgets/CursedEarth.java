package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CursedEarth extends SlimefunItem {

    @Getter
    private final double ticksToSpawn;
    @Getter
    private final int lightLevel;
    @Getter
    private final List<EntityType> spawns;
    @Getter
    private final Particle.DustOptions dustOptions;
    @Getter
    private int currentTick = 0;

    @ParametersAreNonnullByDefault
    public CursedEarth(ItemGroup category,
                       SlimefunItemStack item,
                       RecipeType recipeType,
                       ItemStack[] recipe,
                       double ticksToSpawn,
                       int lightLevel,
                       List<EntityType> spawns,
                       Color color
    ) {
        super(category, item, recipeType, recipe);
        this.ticksToSpawn = ticksToSpawn;
        this.lightLevel = lightLevel;
        this.spawns = spawns;
        this.dustOptions = new Particle.DustOptions(color, 1);
    }

    @Override
    public void preRegister() {
        addItemHandler(onTick());
    }

    private BlockTicker onTick() {
        return new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                final Location location = block.getLocation().add(0.5, 1.5, 0.5);
                if (currentTick == ticksToSpawn) {
                    final Block blockA = block.getRelative(BlockFace.UP);
                    final Block blockB = blockA.getRelative(BlockFace.UP);
                    if (blockA.getLightLevel() <= lightLevel
                        && blockA.isEmpty()
                        && blockB.isEmpty()
                        && location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5).isEmpty()
                        && location.getWorld().getNearbyEntities(location, 4, 4, 4, LivingEntity.class::isInstance).size() < 10
                    ) {
                        location.getWorld().spawnEntity(
                            location,
                            spawns.get(ThreadLocalRandom.current().nextInt(spawns.size())),
                            true
                        );
                    }
                    currentTick = 0;
                } else {
                    currentTick++;
                }
                ParticleUtils.displayParticleEffect(
                    location,
                    1,
                    3,
                    dustOptions
                );
            }
        };
    }
}
