package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Displacer extends LimitedUseItem {

    private static final NamespacedKey key = Keys.newKey("uses");

    private static final Map<Material, Material> CONVERSION_MAP_MATERIAL = new EnumMap<>(Material.class);
    private static final Map<EntityType, EntityType> CONVERSION_MAP_ENTITY = new EnumMap<>(EntityType.class);

    static {
        CONVERSION_MAP_MATERIAL.put(Material.ICE, Material.WATER);
        CONVERSION_MAP_MATERIAL.put(Material.PACKED_ICE, Material.ICE);
        CONVERSION_MAP_MATERIAL.put(Material.BLUE_ICE, Material.PACKED_ICE);
        CONVERSION_MAP_MATERIAL.put(Material.CAMPFIRE, Material.SOUL_CAMPFIRE);
        CONVERSION_MAP_MATERIAL.put(Material.SOUL_CAMPFIRE, Material.CAMPFIRE);
        CONVERSION_MAP_MATERIAL.put(Material.TORCH, Material.SOUL_TORCH);
        CONVERSION_MAP_MATERIAL.put(Material.SOUL_TORCH, Material.TORCH);
        CONVERSION_MAP_MATERIAL.put(Material.LANTERN, Material.SOUL_LANTERN);
        CONVERSION_MAP_MATERIAL.put(Material.SOUL_LANTERN, Material.LANTERN);
        CONVERSION_MAP_MATERIAL.put(Material.PUMPKIN, Material.CARVED_PUMPKIN);
        CONVERSION_MAP_MATERIAL.put(Material.CARVED_PUMPKIN, Material.JACK_O_LANTERN);
        CONVERSION_MAP_MATERIAL.put(Material.JACK_O_LANTERN, Material.CARVED_PUMPKIN);

        CONVERSION_MAP_ENTITY.put(EntityType.SQUID, EntityType.GLOW_SQUID);
        CONVERSION_MAP_ENTITY.put(EntityType.GLOW_SQUID, EntityType.SQUID);
        CONVERSION_MAP_ENTITY.put(EntityType.ZOMBIE, EntityType.DROWNED);
        CONVERSION_MAP_ENTITY.put(EntityType.DROWNED, EntityType.HUSK);
        CONVERSION_MAP_ENTITY.put(EntityType.HUSK, EntityType.ZOMBIE);
        CONVERSION_MAP_ENTITY.put(EntityType.SKELETON, EntityType.STRAY);
        CONVERSION_MAP_ENTITY.put(EntityType.STRAY, EntityType.SKELETON);
        CONVERSION_MAP_ENTITY.put(EntityType.SPIDER, EntityType.CAVE_SPIDER);
        CONVERSION_MAP_ENTITY.put(EntityType.CAVE_SPIDER, EntityType.SPIDER);
        CONVERSION_MAP_ENTITY.put(EntityType.SHEEP, EntityType.COW);
        CONVERSION_MAP_ENTITY.put(EntityType.COW, EntityType.SHEEP);
        CONVERSION_MAP_ENTITY.put(EntityType.CAT, EntityType.OCELOT);
        CONVERSION_MAP_ENTITY.put(EntityType.OCELOT, EntityType.CAT);
    }

    public Displacer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(itemGroup, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            final Player player = e.getPlayer();
            final Optional<Block> blockOptional = e.getClickedBlock();
            final Optional<Entity> entityOptional = Optional.ofNullable(player.getTargetEntity(10, false));

            e.cancel();

            blockOptional.ifPresent(block -> blocks(player, block));
            entityOptional.ifPresent(entity -> entities(player, entity));

            damageItem(player, e.getItem());
        };
    }

    private void blocks(@Nonnull Player player, @Nonnull Block block) {
        if (!GeneralUtils.hasPermission(player, block, Interaction.BREAK_BLOCK) && BlockStorage.check(block) != null) {
            return;
        }

        Material material = block.getType();
        Material convertTo = CONVERSION_MAP_MATERIAL.get(material);

        if (convertTo != null) {
            block.setType(convertTo, true);
        } else if (material == Material.FARMLAND) {
            farmland(block);
        }
    }

    private void farmland(@Nonnull Block block) {
        Farmland farmland = (Farmland) block.getBlockData();
        farmland.setMoisture(farmland.getMaximumMoisture());
    }

    private void entities(@Nonnull Player player, @Nonnull Entity entity) {
        if (!GeneralUtils.hasPermission(player, entity.getLocation(), Interaction.INTERACT_ENTITY)
            || !GeneralUtils.hasPermission(player, entity.getLocation(), Interaction.ATTACK_ENTITY)
        ) {
            return;
        }

        final EntityType entityType = entity.getType();
        final EntityType convertTo = CONVERSION_MAP_ENTITY.get(entityType);

        if (convertTo != null) {
            entity.getWorld().spawnEntity(entity.getLocation(), convertTo, false);
            entity.remove();
        } else if (entityType == EntityType.ITEM_FRAME || entityType == EntityType.GLOW_ITEM_FRAME) {
            itemFrame((ItemFrame) entity);
        } else if (entityType == EntityType.ZOMBIE_VILLAGER) {
            zombieVillager(player, (ZombieVillager) entity);
        }
    }

    private void itemFrame(@Nonnull ItemFrame itemFrame) {
        if (itemFrame.getItem().getType() != Material.AIR) {
            itemFrame.setVisible(!itemFrame.isVisible());
        }
    }

    private void zombieVillager(@Nonnull Player player, @Nonnull ZombieVillager zombieVillager) {
        zombieVillager.setConversionPlayer(player);
        zombieVillager.setConversionTime(200);
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }
}
