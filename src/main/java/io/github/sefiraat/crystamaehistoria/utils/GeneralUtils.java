package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public final class GeneralUtils {

    /**
     * Tests a chance roll starting from 1 to upper
     *
     * @param chance The number the roll must be lower than
     * @param upper  The highest possible number that could roll (inclusive)
     * @return true if roll passes
     */
    public static boolean testChance(int chance, int upper) {
        return roll(upper, true) <= chance;
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper   The highest possible number that could roll (inclusive)
     * @param upLimit If true, the bound will be increased for 1 for inclusivity while
     *                maintaining readability for manually typed numbers
     *                (i.e. Upper 50 converts to 51 returning a max of 50 still).
     *                Set false when using 0 based indexes (list.size())
     * @return rolled int
     */
    public static int roll(int upper, boolean upLimit) {
        if (upLimit) {
            upper++;
        }
        return ThreadLocalRandom.current().nextInt(1, upper);
    }

    /**
     * Tests a chance roll starting from 1 to {@param upper}
     *
     * @param chance The number the roll must be lower than
     * @param upper  The highest possible number that could roll (inclusive)
     * @return true if roll passes
     */
    public static boolean testChance(double chance, double upper) {
        return roll(upper, true) <= chance;
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper   The highest possible number that could roll (inclusive)
     * @param upLimit If true, the bound will be increased for 1 for inclusivity while
     *                maintaining readability for manually typed numbers
     *                (i.e. Upper 50 converts to 51 returning a max of 50 still).
     *                Set false when using 0 based indexes (list.size())
     * @return rolled int
     */
    public static double roll(double upper, boolean upLimit) {
        if (upLimit) {
            upper++;
        }
        return ThreadLocalRandom.current().nextDouble(1, upper);
    }

    /**
     * Rolls a number starting from 0 to upper
     *
     * @param upper The highest possible number that could roll (inclusive)
     * @return rolled int
     */
    public static int roll(int upper) {
        return roll(upper, true);
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper The highest possible number that could roll (inclusive)
     * @return rolled int
     */
    public static double roll(double upper) {
        return roll(upper, true);
    }

    @ParametersAreNonnullByDefault
    public static void markBlockForRemoval(Block block, int secondsUntilRemoval) {
        long timeUntilRemoval = secondsUntilRemoval * 1000L;
        block.setMetadata("ch", new FixedMetadataValue(CrystamaeHistoria.getInstance(), "y"));
        long removalTime = System.currentTimeMillis() + timeUntilRemoval;
        CrystamaeHistoria.getSpellMemory().getBlocksToRemove().put(new BlockPosition(block), removalTime);
    }

    @ParametersAreNonnullByDefault
    public static boolean isRemovableBlock(Block block) {
        return block.hasMetadata("ch");
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Block block, Interaction interaction) {
        return hasPermission(player.getUniqueId(), block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Location location, Interaction interaction) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
        if (interaction == Interaction.ATTACK_PLAYER && !location.getWorld().getPVP()) {
            return false;
        }
        return Slimefun.getProtectionManager().hasPermission(offlinePlayer, location, interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Location location, Interaction interaction) {
        return hasPermission(player.getUniqueId(), location, interaction);
    }

    @ParametersAreNonnullByDefault
    public boolean pullEntity(UUID caster, Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
                              .subtract(pullToLocation.toVector())
                              .normalize()
                              .multiply(-force);
        return pushEntity(caster, vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public void pullEntity(Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
                              .subtract(pullToLocation.toVector())
                              .normalize()
                              .multiply(-force);
        pushEntity(vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public boolean pushEntity(UUID caster, Vector vector, Entity pushed) {
        Interaction interaction = pushed instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.INTERACT_ENTITY;
        if (GeneralUtils.hasPermission(caster, pushed.getLocation(), interaction)) {
            pushed.setVelocity(vector);
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public void pushEntity(Vector vector, Entity pushed) {
        pushed.setVelocity(vector);
    }

    @ParametersAreNonnullByDefault
    public boolean pushEntity(UUID caster, Vector pushVector, Entity pushed, double force) {
        return pushEntity(caster, pushVector.multiply(force), pushed);
    }

    @ParametersAreNonnullByDefault
    public static boolean tryBreakBlock(UUID caster, Block block) {
        Player player = Bukkit.getPlayer(caster);
        if (GeneralUtils.blockCanBeBroken(caster, block)
            && player != null
        ) {
            block.breakNaturally(player.getInventory().getItemInMainHand());
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean blockCanBeBroken(UUID caster, Block block) {
        return hasPermission(caster, block, Interaction.BREAK_BLOCK)
            && !BlockStorage.hasBlockInfo(block)
            && !(block.getState() instanceof TileState)
            && block.getType().getHardness() != -1
            && !block.getType().isAir();
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Block block, Interaction interaction) {
        return hasPermission(player, block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID caster, double damage) {
        return damageEntity(livingEntity, caster, damage, null, 0);
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity,
                                       UUID caster,
                                       double damage,
                                       @Nullable Location knockbackOrigin,
                                       double knockbackForce
    ) {
        Interaction interaction = livingEntity instanceof Player ?
                                  Interaction.ATTACK_PLAYER :
                                  Interaction.ATTACK_ENTITY;
        if (GeneralUtils.hasPermission(caster, livingEntity.getLocation(), interaction)) {
            Player player = Bukkit.getPlayer(caster);
            livingEntity.damage(damage, player);
            if (knockbackOrigin != null && knockbackForce > 0) {
                GeneralUtils.pushEntity(caster, knockbackOrigin, livingEntity, knockbackForce);
            }
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public boolean pushEntity(UUID caster, Location pushFromLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
                              .subtract(pushFromLocation.toVector())
                              .normalize()
                              .multiply(force);
        return pushEntity(caster, vector, pushed);
    }

    /**
     * Heal the entity by the provided amount
     *
     * @param livingEntity The {@link LivingEntity} to heal
     * @param healAmount   The amount to heal by
     */
    @ParametersAreNonnullByDefault
    public static void healEntity(LivingEntity livingEntity, double healAmount) {
        AttributeInstance attribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            livingEntity.setHealth(Math.min(attribute.getValue(), livingEntity.getHealth() + healAmount));
        }
    }

    @ParametersAreNonnullByDefault
    public static Item spawnDisplayItem(ItemStack stack, Location location, String name) {
        final Item item = location.getWorld().dropItem(
            location,
            stack
        );
        PersistentDataAPI.setBoolean(item, Keys.PDC_IS_DISPLAY_ITEM, true);
        item.setCustomName(name);
        item.setCustomNameVisible(true);
        item.setGravity(false);
        item.setVelocity(new Vector(0, 0, 0));
        item.setCanPlayerPickup(false);
        item.setPickupDelay(Integer.MAX_VALUE);
        return item;
    }

    /**
     * Heal the entity by the provided amount
     *
     * @param itemStack         The {@link LivingEntity} to heal
     * @param durationInSeconds The amount to heal by
     */
    @ParametersAreNonnullByDefault
    public static void putOnCooldown(ItemStack itemStack, int durationInSeconds) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            PersistentDataAPI.setLong(
                itemMeta,
                Keys.PDC_ON_COOLDOWN,
                System.currentTimeMillis() + (durationInSeconds * 1000L)
            );
            itemStack.setItemMeta(itemMeta);
        }
    }

    /**
     * Heal the entity by the provided amount
     *
     * @param itemStack The {@link LivingEntity} to heal
     */
    @ParametersAreNonnullByDefault
    public static boolean isOnCooldown(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            long cooldownUntil = PersistentDataAPI.getLong(itemMeta, Keys.PDC_ON_COOLDOWN, 0);
            return System.currentTimeMillis() < cooldownUntil;
        }
        return false;
    }

    @Nonnull
    public static ItemStack getPreEnchantedItemStack(Material material) {
        return getPreEnchantedItemStack(material, true, new Pair<>(Enchantment.LURE, 1));
    }

    @Nonnull
    @SafeVarargs
    public static ItemStack getPreEnchantedItemStack(Material material,
                                                     boolean hide,
                                                     @Nonnull Pair<Enchantment, Integer>... enchantments
    ) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant(pair.getFirstValue(), pair.getSecondValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
