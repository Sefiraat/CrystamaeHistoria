package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class MobMat extends SlimefunItem {

    @Getter
    private final double damage;
    @Getter
    private final boolean allowPlayerDrops;

    @ParametersAreNonnullByDefault
    public MobMat(ItemGroup category,
                  SlimefunItemStack item,
                  RecipeType recipeType,
                  ItemStack[] recipe,
                  double damage,
                  boolean allowPlayerDrops
    ) {
        super(category, item, recipeType, recipe);
        this.damage = damage;
        this.allowPlayerDrops = allowPlayerDrops;
        this.addItemHandler(
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent event) {
                    BlockStorage.addBlockInfo(event.getBlock(), "CH_UUID", event.getPlayer().getUniqueId().toString());
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                    BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    final Location location = block.getLocation().add(0.5, 0.5, 0.5);
                    @NotNull Collection<Entity> entities = location.getWorld().getNearbyEntities(
                        location,
                        0.5,
                        0.5,
                        0.5,
                        LivingEntity.class::isInstance
                    );
                    for (Entity entity : entities) {
                        final LivingEntity livingEntity = (LivingEntity) entity;
                        final UUID uuid = UUID.fromString(BlockStorage.getLocationInfo(block.getLocation(), "CH_UUID"));
                        if (allowPlayerDrops && Bukkit.getOfflinePlayer(uuid).isOnline()) {
                            Player player = Bukkit.getPlayer(uuid);
                            livingEntity.damage(damage, player);
                        } else {
                            livingEntity.damage(damage);
                        }
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                        ParticleUtils.displayParticleEffect(location, 1, 3, dustOptions);
                    }
                }
            }
        );
    }
}
