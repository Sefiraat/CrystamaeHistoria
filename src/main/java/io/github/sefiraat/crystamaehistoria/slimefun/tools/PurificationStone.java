package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import scala.collection.IndexedSeq;
import scala.collection.mutable.LinkedHashSet;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class PurificationStone extends LimitedUseItem {

    private static final Map<EntityType, Consumer<Entity>> TYPE_CONSUMER_MAP = new EnumMap<>(EntityType.class);
    private static final Map<Material, Consumer<Block>> MATERIAL_CONSUMER_MAP = new HashMap<>();

    private static final NamespacedKey key = Keys.newKey("uses");

    static {
        TYPE_CONSUMER_MAP.put(EntityType.ZOMBIE_VILLAGER, entity -> {
            ZombieVillager zombieVillager = (ZombieVillager) entity;
            zombieVillager.setConversionTime(1);
        });
        TYPE_CONSUMER_MAP.put(EntityType.ZOMBIE, entity -> {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).damage(999);
            }
        });
        TYPE_CONSUMER_MAP.put(EntityType.ZOMBIE_HORSE, entity -> {
            entity.getWorld().spawnEntity(entity.getLocation(), EntityType.HORSE, true);
            entity.remove();
        });
        TYPE_CONSUMER_MAP.put(EntityType.PLAYER, entity -> {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                for (PotionEffect activePotionEffect : player.getActivePotionEffects()) {
                    player.removePotionEffect(activePotionEffect.getType());
                }
            }
        });
    }

    public PurificationStone(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
        setMaxUseCount(50);
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return new ItemUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent event) {
                final Block block = event.getPlayer().getTargetBlockExact(100);
                final Entity entity = event.getPlayer().getTargetEntity(100);

                if (entity != null && GeneralUtils.hasPermission(event.getPlayer(), entity.getLocation(), Interaction.INTERACT_ENTITY)) {
                    tryPurifyEntity(event.getPlayer(), entity, event.getItem());
                    return;
                }

                if (block != null && GeneralUtils.hasPermission(event.getPlayer(), block, Interaction.PLACE_BLOCK)) {
                    tryPurifyBlock(event.getPlayer(), block, event.getItem());
                }
            }
        };
    }

    private void tryPurifyEntity(Player player, Entity entity, ItemStack itemStack) {
        Consumer<Entity> entityConsumer = TYPE_CONSUMER_MAP.get(entity.getType());
        if (entityConsumer != null) {
            ParticleUtils.displayParticleEffect(entity, Particle.WAX_OFF, 2, 10);
            entityConsumer.accept(entity);
            damageItem(player, itemStack);
        }
    }

    private void tryPurifyBlock(Player player, Block block, ItemStack itemStack) {
        final Material material = block.getType();
        Set<Material> copperSet = null;

        if (CrystaTag.COPPER_BLOCKS.isTagged(material)) {
            copperSet = CrystaTag.COPPER_BLOCKS.getValues();
        } else if (CrystaTag.COPPER_BLOCKS_WAXED.isTagged(material)) {
            copperSet = CrystaTag.COPPER_BLOCKS_WAXED.getValues();
        } else if (CrystaTag.CUT_COPPER_BLOCKS.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_BLOCKS.getValues();
        } else if (CrystaTag.CUT_COPPER_BLOCKS_WAXED.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_BLOCKS_WAXED.getValues();
        } else if (CrystaTag.CUT_COPPER_SLABS.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_SLABS.getValues();
        } else if (CrystaTag.CUT_COPPER_SLABS_WAXED.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_SLABS_WAXED.getValues();
        } else if (CrystaTag.CUT_COPPER_STAIRS.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_STAIRS.getValues();
        } else if (CrystaTag.CUT_COPPER_STAIRS_WAXED.isTagged(material)) {
            copperSet = CrystaTag.CUT_COPPER_STAIRS_WAXED.getValues();
        }

        if (copperSet != null) {
            ParticleUtils.displayParticleEffect(block.getLocation(), Particle.WAX_OFF, 2, 10);
            rollBackCopper(block, copperSet, material);
            damageItem(player, itemStack);
            return;
        }

        final Consumer<Block> entityConsumer = MATERIAL_CONSUMER_MAP.get(material);

        if (entityConsumer != null) {
            ParticleUtils.displayParticleEffect(block.getLocation(), Particle.WAX_OFF, 2, 10);
            entityConsumer.accept(block);
            damageItem(player, itemStack);
        }
    }

    private void rollBackCopper(Block block, Set<Material> materialSet, Material material) {
        LinkedHashSet<Material> linkedHashSet = (LinkedHashSet<Material>) materialSet;
        IndexedSeq<Material> indexedSeq = linkedHashSet.toIndexedSeq();
        int index = indexedSeq.indexOf(material);
        if (index > 0) {
            block.setType(indexedSeq.apply(index - 1));
        }
    }

    @Nonnull
    @Override
    protected NamespacedKey getStorageKey() {
        return key;
    }
}
