package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MobTrap extends TickingBlockNoGui {

    @Getter
    private final Map<Location, PotionEffectType> potionEffectTypeMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public MobTrap(ItemGroup category,
                   SlimefunItemStack item,
                   RecipeType recipeType,
                   ItemStack[] recipe
    ) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onBlockUse());
    }

    private BlockUseHandler onBlockUse() {
        return e -> {
            final ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
            final Optional<Block> optionalBlock = e.getClickedBlock();

            if (itemStack.getType() == Material.POTION && optionalBlock.isPresent()) {
                final Block block = optionalBlock.get();
                final PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
                final PotionEffectType type = potionMeta.getBasePotionData().getType().getEffectType();
                if (type != null) {
                    BlockStorage.addBlockInfo(block, "POT_EFF", type.getName());
                    potionEffectTypeMap.put(block.getLocation(), type);
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }
            }
        };
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        String potionEffectString = BlockStorage.getLocationInfo(block.getLocation(), "POT_EFF");
        if (potionEffectString != null) {
            potionEffectTypeMap.put(
                block.getLocation(),
                PotionEffectType.getByName(potionEffectString)
            );
        }
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        final Location location = block.getLocation().add(0.5, 0.5, 0.5);
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.AQUA, 1);
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(
            location,
            0.5,
            0.5,
            0.5,
            LivingEntity.class::isInstance
        );
        PotionEffectType type = potionEffectTypeMap.get(block.getLocation());
        if (type != null) {
            for (Entity entity : entities) {
                final LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addPotionEffect(new PotionEffect(type, 40, 0));
                ParticleUtils.displayParticleEffect(location, 1, 3, dustOptions);
            }
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        // Not required
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
        BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
    }
}
