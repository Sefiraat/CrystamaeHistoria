package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.GildingUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class PrismaticGilderCache extends AbstractCache {

    protected static final String AMOUNT = "ch_amount";

    private final int maxVolume;
    private int fillAmount;

    @ParametersAreNonnullByDefault
    public PrismaticGilderCache(BlockMenu blockMenu, int maxVolume) {
        super(blockMenu);
        this.maxVolume = maxVolume;

        final String activePlayerString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_ACTIVE_PLAYER);
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        final Location location = blockMenu.getLocation();
        final Collection<Entity> entitiesToPull = location.getWorld().getNearbyEntities(
            location.clone().add(0.5, 0.5, 0.5),
            3,
            3,
            3,
            Item.class::isInstance
        );

        for (Entity entity : entitiesToPull) {
            final Item item = (Item) entity;
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());

            if (slimefunItem != null && slimefunItem.equals(Materials.getPrismaticCrystal())) {
                GeneralUtils.pullEntity(location, entity, 0.5);
            }
        }

        final Collection<Entity> entities = location.getWorld().getNearbyEntities(
            location.clone().add(0.5, 0.5, 0.5),
            0.75,
            0.75,
            0.75,
            Item.class::isInstance
        );

        for (Entity entity : entities) {
            final Item item = (Item) entity;
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());

            if (slimefunItem != null && slimefunItem.equals(Materials.getPrismaticCrystal())) {
                addCrystamae(item);
            } else {
                rejectItem(item);
            }
            syncBlock();
        }
    }

    @ParametersAreNonnullByDefault
    public void gildItem(Block block, ItemStack heldItem, Player player) {
        final Location location = block.getLocation().clone().add(0.5, 1.5, 0.5);

        if (heldItem.getType() != Material.AIR
            && StoryUtils.isStoried(heldItem)
            && !StoryUtils.hasRemainingStorySlots(heldItem)
            && !GildingUtils.isGilded(heldItem)
        ) {

            final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(heldItem.getType());

            if (this.fillAmount >= definition.getBlockTier().tier) {
                final ItemStack gildedStack = heldItem.asQuantity(1);

                this.fillAmount -= definition.getBlockTier().tier;
                GildingUtils.makeGilded(gildedStack);

                final DisplayItem displayItem = new DisplayItem(
                    gildedStack,
                    location,
                    "",
                    item -> {
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.YELLOW, 1);
                        ParticleUtils.displayParticleEffect(item, 1.5, 20, dustOptions);
                        location.getWorld().dropItem(location, gildedStack);
                    }
                );
                heldItem.setAmount(heldItem.getAmount() - 1);
                displayItem.registerRemoval(2000);
                PlayerStatistics.unlockStoryGilded(player.getUniqueId(), definition);
            } else {
                ParticleUtils.displayParticleEffect(location, Particle.CRIT_MAGIC, 1, 3);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectItem(Item item) {
        final double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        final double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        item.setVelocity(new Vector(velX, 0.5, velZ));
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(Item item) {
        final int numberInStack = item.getItemStack().getAmount();
        if (this.fillAmount >= maxVolume) {
            rejectItem(item);
        } else {
            this.fillAmount = Math.min(maxVolume, this.fillAmount + 1);
            if (numberInStack > 1) {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, numberInStack - 1);
            } else {
                item.remove();
            }
        }
        ParticleUtils.displayParticleEffect(this.blockMenu.getLocation(), Particle.FLASH, 1, 5);
    }

    public void syncBlock() {
        BlockStorage.addBlockInfo(blockMenu.getBlock(), AMOUNT, String.valueOf(this.fillAmount));
    }

    public int getFillAmount() {
        return this.fillAmount;
    }

    public void setFillAmount(int fillAmount) {
        this.fillAmount = fillAmount;
    }
}
