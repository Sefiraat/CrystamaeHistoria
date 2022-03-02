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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Displacer extends LimitedUseItem {

    private static final NamespacedKey key = Keys.newKey("uses");

    private static final Map<Material, Material> CONVERSIONS = new EnumMap<>(Material.class);
    private static final Map<EntityType, EntityType> ENTITY_CONVERSIONS = new EnumMap<>(EntityType.class);

    static {
        CONVERSIONS.put(Material.ICE, Material.WATER);
        CONVERSIONS.put(Material.PACKED_ICE, Material.ICE);
        CONVERSIONS.put(Material.BLUE_ICE, Material.PACKED_ICE);
        CONVERSIONS.put(Material.CAMPFIRE, Material.SOUL_CAMPFIRE);
        CONVERSIONS.put(Material.SOUL_CAMPFIRE, Material.CAMPFIRE);
        CONVERSIONS.put(Material.TORCH, Material.SOUL_TORCH);
        CONVERSIONS.put(Material.SOUL_TORCH, Material.TORCH);
        CONVERSIONS.put(Material.LANTERN, Material.SOUL_LANTERN);
        CONVERSIONS.put(Material.SOUL_LANTERN, Material.LANTERN);
        CONVERSIONS.put(Material.PUMPKIN, Material.CARVED_PUMPKIN);
        CONVERSIONS.put(Material.CARVED_PUMPKIN, Material.JACK_O_LANTERN);
        CONVERSIONS.put(Material.JACK_O_LANTERN, Material.CARVED_PUMPKIN);
        CONVERSIONS.put(Material.SAND, Material.RED_SAND);
        CONVERSIONS.put(Material.RED_SAND, Material.SOUL_SAND);
        CONVERSIONS.put(Material.SOUL_SAND, Material.SAND);
        
        CONVERSIONS.put(Material.BLACK_CONCRETE, Material.WHITE_CONCRETE);
        CONVERSIONS.put(Material.WHITE_CONCRETE, Material.ORANGE_CONCRETE);
        CONVERSIONS.put(Material.ORANGE_CONCRETE, Material.MAGENTA_CONCRETE);
        CONVERSIONS.put(Material.MAGENTA_CONCRETE, Material.LIGHT_BLUE_CONCRETE);
        CONVERSIONS.put(Material.LIGHT_BLUE_CONCRETE, Material.YELLOW_CONCRETE);
        CONVERSIONS.put(Material.YELLOW_CONCRETE, Material.LIME_CONCRETE);
        CONVERSIONS.put(Material.LIME_CONCRETE, Material.PINK_CONCRETE);
        CONVERSIONS.put(Material.PINK_CONCRETE, Material.GRAY_CONCRETE);
        CONVERSIONS.put(Material.GRAY_CONCRETE, Material.LIGHT_GRAY_CONCRETE);
        CONVERSIONS.put(Material.LIGHT_GRAY_CONCRETE, Material.CYAN_CONCRETE);
        CONVERSIONS.put(Material.CYAN_CONCRETE, Material.PURPLE_CONCRETE);
        CONVERSIONS.put(Material.PURPLE_CONCRETE, Material.BLUE_CONCRETE);
        CONVERSIONS.put(Material.BLUE_CONCRETE, Material.BROWN_CONCRETE);
        CONVERSIONS.put(Material.BROWN_CONCRETE, Material.GREEN_CONCRETE);
        CONVERSIONS.put(Material.GREEN_CONCRETE, Material.RED_CONCRETE);
        CONVERSIONS.put(Material.RED_CONCRETE, Material.BLACK_CONCRETE);
        
        CONVERSIONS.put(Material.BLACK_CONCRETE_POWDER, Material.WHITE_CONCRETE_POWDER);
        CONVERSIONS.put(Material.WHITE_CONCRETE_POWDER, Material.ORANGE_CONCRETE_POWDER);
        CONVERSIONS.put(Material.ORANGE_CONCRETE_POWDER, Material.MAGENTA_CONCRETE_POWDER);
        CONVERSIONS.put(Material.MAGENTA_CONCRETE_POWDER, Material.LIGHT_BLUE_CONCRETE_POWDER);
        CONVERSIONS.put(Material.LIGHT_BLUE_CONCRETE_POWDER, Material.YELLOW_CONCRETE_POWDER);
        CONVERSIONS.put(Material.YELLOW_CONCRETE_POWDER, Material.LIME_CONCRETE_POWDER);
        CONVERSIONS.put(Material.LIME_CONCRETE_POWDER, Material.PINK_CONCRETE_POWDER);
        CONVERSIONS.put(Material.PINK_CONCRETE_POWDER, Material.GRAY_CONCRETE_POWDER);
        CONVERSIONS.put(Material.GRAY_CONCRETE_POWDER, Material.LIGHT_GRAY_CONCRETE_POWDER);
        CONVERSIONS.put(Material.LIGHT_GRAY_CONCRETE_POWDER, Material.CYAN_CONCRETE_POWDER);
        CONVERSIONS.put(Material.CYAN_CONCRETE_POWDER, Material.PURPLE_CONCRETE_POWDER);
        CONVERSIONS.put(Material.PURPLE_CONCRETE_POWDER, Material.BLUE_CONCRETE_POWDER);
        CONVERSIONS.put(Material.BLUE_CONCRETE_POWDER, Material.BROWN_CONCRETE_POWDER);
        CONVERSIONS.put(Material.BROWN_CONCRETE_POWDER, Material.GREEN_CONCRETE_POWDER);
        CONVERSIONS.put(Material.GREEN_CONCRETE_POWDER, Material.RED_CONCRETE_POWDER);
        CONVERSIONS.put(Material.RED_CONCRETE_POWDER, Material.BLACK_CONCRETE_POWDER);
        
        CONVERSIONS.put(Material.BLACK_WOOL, Material.WHITE_WOOL);
        CONVERSIONS.put(Material.WHITE_WOOL, Material.ORANGE_WOOL);
        CONVERSIONS.put(Material.ORANGE_WOOL, Material.MAGENTA_WOOL);
        CONVERSIONS.put(Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL);
        CONVERSIONS.put(Material.LIGHT_BLUE_WOOL, Material.YELLOW_WOOL);
        CONVERSIONS.put(Material.YELLOW_WOOL, Material.LIME_WOOL);
        CONVERSIONS.put(Material.LIME_WOOL, Material.PINK_WOOL);
        CONVERSIONS.put(Material.PINK_WOOL, Material.GRAY_WOOL);
        CONVERSIONS.put(Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL);
        CONVERSIONS.put(Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL);
        CONVERSIONS.put(Material.CYAN_WOOL, Material.PURPLE_WOOL);
        CONVERSIONS.put(Material.PURPLE_WOOL, Material.BLUE_WOOL);
        CONVERSIONS.put(Material.BLUE_WOOL, Material.BROWN_WOOL);
        CONVERSIONS.put(Material.BROWN_WOOL, Material.GREEN_WOOL);
        CONVERSIONS.put(Material.GREEN_WOOL, Material.RED_WOOL);
        CONVERSIONS.put(Material.RED_WOOL, Material.BLACK_WOOL);
        
        CONVERSIONS.put(Material.BLACK_GLAZED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.WHITE_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.ORANGE_GLAZED_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.MAGENTA_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.YELLOW_GLAZED_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.LIME_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.PINK_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.GRAY_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.CYAN_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.PURPLE_GLAZED_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.BLUE_GLAZED_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.BROWN_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.GREEN_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
        CONVERSIONS.put(Material.RED_GLAZED_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
        
        CONVERSIONS.put(Material.BLACK_TERRACOTTA, Material.WHITE_TERRACOTTA);
        CONVERSIONS.put(Material.WHITE_TERRACOTTA, Material.ORANGE_TERRACOTTA);
        CONVERSIONS.put(Material.ORANGE_TERRACOTTA, Material.MAGENTA_TERRACOTTA);
        CONVERSIONS.put(Material.MAGENTA_TERRACOTTA, Material.LIGHT_BLUE_TERRACOTTA);
        CONVERSIONS.put(Material.LIGHT_BLUE_TERRACOTTA, Material.YELLOW_TERRACOTTA);
        CONVERSIONS.put(Material.YELLOW_TERRACOTTA, Material.LIME_TERRACOTTA);
        CONVERSIONS.put(Material.LIME_TERRACOTTA, Material.PINK_TERRACOTTA);
        CONVERSIONS.put(Material.PINK_TERRACOTTA, Material.GRAY_TERRACOTTA);
        CONVERSIONS.put(Material.GRAY_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA);
        CONVERSIONS.put(Material.LIGHT_GRAY_TERRACOTTA, Material.CYAN_TERRACOTTA);
        CONVERSIONS.put(Material.CYAN_TERRACOTTA, Material.PURPLE_TERRACOTTA);
        CONVERSIONS.put(Material.PURPLE_TERRACOTTA, Material.BLUE_TERRACOTTA);
        CONVERSIONS.put(Material.BLUE_TERRACOTTA, Material.BROWN_TERRACOTTA);
        CONVERSIONS.put(Material.BROWN_TERRACOTTA, Material.GREEN_TERRACOTTA);
        CONVERSIONS.put(Material.GREEN_TERRACOTTA, Material.RED_TERRACOTTA);
        CONVERSIONS.put(Material.RED_TERRACOTTA, Material.BLACK_TERRACOTTA);
        
        CONVERSIONS.put(Material.BLACK_STAINED_GLASS, Material.WHITE_STAINED_GLASS);
        CONVERSIONS.put(Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS);
        CONVERSIONS.put(Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS);
        CONVERSIONS.put(Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS);
        CONVERSIONS.put(Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS);
        CONVERSIONS.put(Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS);
        CONVERSIONS.put(Material.LIME_STAINED_GLASS, Material.PINK_STAINED_GLASS);
        CONVERSIONS.put(Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS);
        CONVERSIONS.put(Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS);
        CONVERSIONS.put(Material.LIGHT_GRAY_STAINED_GLASS, Material.CYAN_STAINED_GLASS);
        CONVERSIONS.put(Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS);
        CONVERSIONS.put(Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS);
        CONVERSIONS.put(Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS);
        CONVERSIONS.put(Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS);
        CONVERSIONS.put(Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS);
        CONVERSIONS.put(Material.RED_STAINED_GLASS, Material.BLACK_STAINED_GLASS);
        
        CONVERSIONS.put(Material.BLACK_STAINED_GLASS_PANE, Material.WHITE_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.WHITE_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.ORANGE_STAINED_GLASS_PANE, Material.MAGENTA_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.MAGENTA_STAINED_GLASS_PANE, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.LIGHT_BLUE_STAINED_GLASS_PANE, Material.YELLOW_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.YELLOW_STAINED_GLASS_PANE, Material.LIME_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.LIME_STAINED_GLASS_PANE, Material.PINK_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.PINK_STAINED_GLASS_PANE, Material.GRAY_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.GRAY_STAINED_GLASS_PANE, Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.CYAN_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.CYAN_STAINED_GLASS_PANE, Material.PURPLE_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.PURPLE_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.BLUE_STAINED_GLASS_PANE, Material.BROWN_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.BROWN_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.GREEN_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE);
        CONVERSIONS.put(Material.RED_STAINED_GLASS_PANE, Material.BLACK_STAINED_GLASS_PANE);
        
        CONVERSIONS.put(Material.BLACK_CARPET, Material.WHITE_CARPET);
        CONVERSIONS.put(Material.WHITE_CARPET, Material.ORANGE_CARPET);
        CONVERSIONS.put(Material.ORANGE_CARPET, Material.MAGENTA_CARPET);
        CONVERSIONS.put(Material.MAGENTA_CARPET, Material.LIGHT_BLUE_CARPET);
        CONVERSIONS.put(Material.LIGHT_BLUE_CARPET, Material.YELLOW_CARPET);
        CONVERSIONS.put(Material.YELLOW_CARPET, Material.LIME_CARPET);
        CONVERSIONS.put(Material.LIME_CARPET, Material.PINK_CARPET);
        CONVERSIONS.put(Material.PINK_CARPET, Material.GRAY_CARPET);
        CONVERSIONS.put(Material.GRAY_CARPET, Material.LIGHT_GRAY_CARPET);
        CONVERSIONS.put(Material.LIGHT_GRAY_CARPET, Material.CYAN_CARPET);
        CONVERSIONS.put(Material.CYAN_CARPET, Material.PURPLE_CARPET);
        CONVERSIONS.put(Material.PURPLE_CARPET, Material.BLUE_CARPET);
        CONVERSIONS.put(Material.BLUE_CARPET, Material.BROWN_CARPET);
        CONVERSIONS.put(Material.BROWN_CARPET, Material.GREEN_CARPET);
        CONVERSIONS.put(Material.GREEN_CARPET, Material.RED_CARPET);
        CONVERSIONS.put(Material.RED_CARPET, Material.BLACK_CARPET);
        
        CONVERSIONS.put(Material.WARPED_FUNGUS, Material.CRIMSON_FUNGUS);
        CONVERSIONS.put(Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS);
        CONVERSIONS.put(Material.WARPED_STEM, Material.CRIMSON_STEM);
        CONVERSIONS.put(Material.CRIMSON_STEM, Material.WARPED_STEM);
        CONVERSIONS.put(Material.WARPED_HYPHAE, Material.CRIMSON_HYPHAE);
        CONVERSIONS.put(Material.CRIMSON_HYPHAE, Material.WARPED_HYPHAE);
        
        CONVERSIONS.put(Material.ACACIA_SAPLING, Material.BIRCH_SAPLING);
        CONVERSIONS.put(Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING);
        CONVERSIONS.put(Material.DARK_OAK_SAPLING, Material.OAK_SAPLING);
        CONVERSIONS.put(Material.OAK_SAPLING, Material.JUNGLE_SAPLING);
        CONVERSIONS.put(Material.JUNGLE_SAPLING, Material.SPRUCE_SAPLING);
        CONVERSIONS.put(Material.SPRUCE_SAPLING, Material.ACACIA_SAPLING);
        
        CONVERSIONS.put(Material.ACACIA_LEAVES, Material.BIRCH_LEAVES);
        CONVERSIONS.put(Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES);
        CONVERSIONS.put(Material.DARK_OAK_LEAVES, Material.OAK_LEAVES);
        CONVERSIONS.put(Material.OAK_LEAVES, Material.JUNGLE_LEAVES);
        CONVERSIONS.put(Material.JUNGLE_LEAVES, Material.SPRUCE_LEAVES);
        CONVERSIONS.put(Material.SPRUCE_LEAVES, Material.ACACIA_LEAVES);
        
        CONVERSIONS.put(Material.VINE, Material.GLOW_LICHEN);
        CONVERSIONS.put(Material.GLOW_LICHEN, Material.VINE);
        
        CONVERSIONS.put(Material.TUBE_CORAL, Material.BRAIN_CORAL);
        CONVERSIONS.put(Material.BRAIN_CORAL, Material.BUBBLE_CORAL);
        CONVERSIONS.put(Material.BUBBLE_CORAL, Material.FIRE_CORAL);
        CONVERSIONS.put(Material.FIRE_CORAL, Material.HORN_CORAL);
        CONVERSIONS.put(Material.HORN_CORAL, Material.TUBE_CORAL);
        
        CONVERSIONS.put(Material.DEAD_TUBE_CORAL, Material.DEAD_BRAIN_CORAL);
        CONVERSIONS.put(Material.DEAD_BRAIN_CORAL, Material.DEAD_BUBBLE_CORAL);
        CONVERSIONS.put(Material.DEAD_BUBBLE_CORAL, Material.DEAD_FIRE_CORAL);
        CONVERSIONS.put(Material.DEAD_FIRE_CORAL, Material.DEAD_HORN_CORAL);
        CONVERSIONS.put(Material.DEAD_HORN_CORAL, Material.DEAD_TUBE_CORAL);

        CONVERSIONS.put(Material.TUBE_CORAL_FAN, Material.BRAIN_CORAL_FAN);
        CONVERSIONS.put(Material.BRAIN_CORAL_FAN, Material.BUBBLE_CORAL_FAN);
        CONVERSIONS.put(Material.BUBBLE_CORAL_FAN, Material.FIRE_CORAL_FAN);
        CONVERSIONS.put(Material.FIRE_CORAL_FAN, Material.HORN_CORAL_FAN);
        CONVERSIONS.put(Material.HORN_CORAL_FAN, Material.TUBE_CORAL_FAN);

        CONVERSIONS.put(Material.DEAD_TUBE_CORAL_FAN, Material.DEAD_BRAIN_CORAL_FAN);
        CONVERSIONS.put(Material.DEAD_BRAIN_CORAL_FAN, Material.DEAD_BUBBLE_CORAL_FAN);
        CONVERSIONS.put(Material.DEAD_BUBBLE_CORAL_FAN, Material.DEAD_FIRE_CORAL_FAN);
        CONVERSIONS.put(Material.DEAD_FIRE_CORAL_FAN, Material.DEAD_HORN_CORAL_FAN);
        CONVERSIONS.put(Material.DEAD_HORN_CORAL_FAN, Material.DEAD_TUBE_CORAL_FAN);

        CONVERSIONS.put(Material.TUBE_CORAL_BLOCK, Material.BRAIN_CORAL_BLOCK);
        CONVERSIONS.put(Material.BRAIN_CORAL_BLOCK, Material.BUBBLE_CORAL_BLOCK);
        CONVERSIONS.put(Material.BUBBLE_CORAL_BLOCK, Material.FIRE_CORAL_BLOCK);
        CONVERSIONS.put(Material.FIRE_CORAL_BLOCK, Material.HORN_CORAL_BLOCK);
        CONVERSIONS.put(Material.HORN_CORAL_BLOCK, Material.TUBE_CORAL_BLOCK);

        CONVERSIONS.put(Material.DEAD_TUBE_CORAL_BLOCK, Material.DEAD_BRAIN_CORAL_BLOCK);
        CONVERSIONS.put(Material.DEAD_BRAIN_CORAL_BLOCK, Material.DEAD_BUBBLE_CORAL_BLOCK);
        CONVERSIONS.put(Material.DEAD_BUBBLE_CORAL_BLOCK, Material.DEAD_FIRE_CORAL_BLOCK);
        CONVERSIONS.put(Material.DEAD_FIRE_CORAL_BLOCK, Material.DEAD_HORN_CORAL_BLOCK);
        CONVERSIONS.put(Material.DEAD_HORN_CORAL_BLOCK, Material.DEAD_TUBE_CORAL_BLOCK);

        ENTITY_CONVERSIONS.put(EntityType.SQUID, EntityType.GLOW_SQUID);
        ENTITY_CONVERSIONS.put(EntityType.GLOW_SQUID, EntityType.SQUID);
        ENTITY_CONVERSIONS.put(EntityType.ZOMBIE, EntityType.DROWNED);
        ENTITY_CONVERSIONS.put(EntityType.DROWNED, EntityType.HUSK);
        ENTITY_CONVERSIONS.put(EntityType.HUSK, EntityType.ZOMBIE);
        ENTITY_CONVERSIONS.put(EntityType.SKELETON, EntityType.STRAY);
        ENTITY_CONVERSIONS.put(EntityType.STRAY, EntityType.SKELETON);
        ENTITY_CONVERSIONS.put(EntityType.SPIDER, EntityType.CAVE_SPIDER);
        ENTITY_CONVERSIONS.put(EntityType.CAVE_SPIDER, EntityType.SPIDER);
        ENTITY_CONVERSIONS.put(EntityType.SHEEP, EntityType.COW);
        ENTITY_CONVERSIONS.put(EntityType.COW, EntityType.SHEEP);
        ENTITY_CONVERSIONS.put(EntityType.CAT, EntityType.OCELOT);
        ENTITY_CONVERSIONS.put(EntityType.OCELOT, EntityType.CAT);
    }

    @ParametersAreNonnullByDefault
    public Displacer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(itemGroup, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            final Player player = e.getPlayer();
            final Optional<Block> blockOptional = e.getClickedBlock();
            final Optional<Entity> entityOptional = Optional.ofNullable(player.getTargetEntity(10, false));

            e.cancel();

            blockOptional.ifPresent(block -> convertBlock(player, block));
            entityOptional.ifPresent(entity -> entities(player, entity));

            damageItem(player, e.getItem());
        };
    }

    private void convertBlock(@Nonnull Player player, @Nonnull Block block) {
        if (GeneralUtils.hasPermission(player, block, Interaction.BREAK_BLOCK)) {
            convertBlock(block);
        }
    }

    private void entities(@Nonnull Player player, @Nonnull Entity entity) {
        if (!GeneralUtils.hasPermission(player, entity.getLocation(), Interaction.INTERACT_ENTITY)
            || !GeneralUtils.hasPermission(player, entity.getLocation(), Interaction.ATTACK_ENTITY)
        ) {
            return;
        }

        final EntityType entityType = entity.getType();
        final EntityType convertTo = ENTITY_CONVERSIONS.get(entityType);

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

    public static void convertBlock(@Nonnull Block block) {
        if (BlockStorage.check(block) != null) {
            return;
        }

        Material material = block.getType();
        Material convertTo = CONVERSIONS.get(material);

        if (convertTo != null) {
            block.setType(convertTo, true);
        } else if (material == Material.FARMLAND) {
            farmland(block);
        } else if (material == Material.DIRT) {
            dirt(block);
        }
    }

    private static void farmland(@Nonnull Block block) {
        Farmland farmland = (Farmland) block.getBlockData();
        farmland.setMoisture(farmland.getMaximumMoisture());
    }

    private static void dirt(@Nonnull Block block) {
        block.setType(block.getY() < 0 ? Material.ROOTED_DIRT : Material.COARSE_DIRT);
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }

    public static Map<Material, Material> getConversions() {
        return CONVERSIONS;
    }
}
