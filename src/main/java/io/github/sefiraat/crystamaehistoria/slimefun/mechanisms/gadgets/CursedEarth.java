package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets;

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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CursedEarth extends SlimefunItem {



    @Getter
    private final double ticksToSpawn;
    @Getter
    private final int lightLevel;
    @Getter
    private final List<EntityType> monsters;
    @Getter
    private final Color color;
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
        this.monsters = spawns;
        this.color = color;
        this.addItemHandler(
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    final Location location = block.getLocation().add(0.5, 1.5, 0.5);
                    final Block blockA = block.getRelative(BlockFace.UP);
                    final Block blockB = blockA.getRelative(BlockFace.UP);
                    if (currentTick == ticksToSpawn) {
                        if (blockA.getLightLevel() <= lightLevel
                            && (blockA.getType().isAir() && blockB.getType().isAir())
                            && location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5).isEmpty()
                            && location.getWorld().getNearbyEntities(location, 4, 4, 4, LivingEntity.class::isInstance).size() < 10
                        ) {
                            location.getWorld().spawnEntity(
                                location,
                                monsters.get(ThreadLocalRandom.current().nextInt(monsters.size())),
                                true
                            );
                        }
                        currentTick = 0;
                    } else {
                        currentTick++;
                    }
                    Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);
                    ParticleUtils.displayParticleEffect(location, 1, 3, dustOptions);
                }
            }
        );
    }
}
