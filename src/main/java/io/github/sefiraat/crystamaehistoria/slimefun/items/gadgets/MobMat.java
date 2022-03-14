package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MobMat extends TickingBlockNoGui {

    @Getter
    private final double damage;
    @Getter
    private final boolean allowPlayerDrops;
    @Getter
    private final Map<Location, UUID> blockOwnerMap = new HashMap<>();

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
        final Location location = block.getLocation().add(0.5, 0.5, 0.5);
        final UUID uuid = blockOwnerMap.get(block.getLocation());
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(
            location,
            0.5,
            0.5,
            0.5,
            LivingEntity.class::isInstance
        );
        for (Entity entity : entities) {
            final LivingEntity livingEntity = (LivingEntity) entity;
            final Player player = Bukkit.getPlayer(uuid);
            if (!allowPlayerDrops || player == null) {
                livingEntity.damage(damage);
            } else {
                CrystamaeHistoria.getSupportedPluginManager().playerDamageWithoutAttribution(livingEntity, player, damage);
            }
            ParticleUtils.displayParticleEffect(location, 1, 3, dustOptions);
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
        BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
    }
}
