package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.BlockRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TrophyDisplay extends TickingBlockNoGui {

    private static final String PDC_ITEM = "itemUUID";

    @Getter
    private final Map<Location, UUID> itemMap = new HashMap<>();
    @Getter
    private final Map<Location, Integer> currentTickMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public TrophyDisplay(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        this.addItemHandler((BlockUseHandler) this::onRightClick);
    }

    @Override
    protected void onFirstTick(@NotNull Block block, @NotNull SlimefunItem slimefunItem, @NotNull Config config) {
        final Location blockLocation = block.getLocation();
        String itemUuidString = BlockStorage.getLocationInfo(block.getLocation(), PDC_ITEM);
        if (itemUuidString != null) {
            itemMap.put(block.getLocation(), UUID.fromString(itemUuidString));
        }
        // Set a random current tick
        TrophyDisplay.this.currentTickMap.put(
            blockLocation,
            ThreadLocalRandom.current().nextInt(3, 7)
        );
    }

    @Override
    protected void onTick(@NotNull Block block, @NotNull SlimefunItem slimefunItem, @NotNull Config config) {
        final Location blockLocation = block.getLocation();
        final UUID currentItemUuid = itemMap.get(blockLocation);

        // If an item isn't in place, then don't do anything
        if (currentItemUuid != null) {
            int currentTick = currentTickMap.get(blockLocation);

            // Only tick periodically
            if (currentTick <= 0 && TrophyDisplay.this.itemMap.containsKey(blockLocation)) {
                final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
                final Location itemLocation = currentItem.getLocation();
                final Location desiredLocation = blockLocation.clone().add(0.5, 1.5, 0.5);

                // Check if item has moved off the platform
                if (itemLocation.distance(desiredLocation) > 0.3) {
                    final ItemStack itemStack = currentItem.getItemStack();
                    blockLocation.getWorld().dropItemNaturally(blockLocation, itemStack);
                    BlockStorage.addBlockInfo(block, PDC_ITEM, null);
                    itemMap.remove(blockLocation);
                    currentItem.remove();
                }

                ParticleUtils.displayParticleEffect(itemLocation.add(0, 0.2, 0), Particle.WAX_ON, 0.2, 3);
                TrophyDisplay.this.currentTickMap.put(blockLocation, ThreadLocalRandom.current().nextInt(4, 8));
            } else {
                currentTick--;
                TrophyDisplay.this.currentTickMap.put(blockLocation, currentTick);
            }
        }
    }

    @Override
    protected void onPlace(@NotNull BlockPlaceEvent event) {
        TrophyDisplay.this.currentTickMap.put(
            event.getBlock().getLocation(),
            ThreadLocalRandom.current().nextInt(3, 7)
        );
    }

    @Override
    protected void onBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
        Location location = blockBreakEvent.getBlock().getLocation();
        final UUID currentItemUuid = itemMap.get(location);
        if (currentItemUuid != null) {
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
            final ItemStack displayStack = currentItem.getItemStack();
            location.getWorld().dropItemNaturally(location, displayStack);
            BlockStorage.clearBlockInfo(location);
            currentItem.remove();
        }
    }

    private void onRightClick(PlayerRightClickEvent e) {
        final Player player = e.getPlayer();

        Optional<Block> optionalBlock = e.getClickedBlock();

        if (!optionalBlock.isPresent()) {
            return;
        }

        e.cancel();

        final Block blockClicked = optionalBlock.get();
        final UUID currentItemUuid = itemMap.get(blockClicked.getLocation());

        // Stand already has an item, we try to remove this then return;
        if (currentItemUuid != null) {
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
            final ItemStack itemStack = currentItem.getItemStack();
            final HashMap<Integer, ItemStack> rejected = player.getInventory().addItem(itemStack);
            BlockStorage.addBlockInfo(blockClicked, PDC_ITEM, null);
            itemMap.remove(blockClicked.getLocation());
            if (rejected.isEmpty()) {
                currentItem.remove();
            }
            return;
        }

        final ItemStack itemStack = e.getItem();
        final Material material = itemStack.getType();
        final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);

        if (definition != null && material != Material.AIR) {
            final BlockRank blockRank = PlayerStatistics.getBlockRank(
                player.getUniqueId(),
                definition
            );
            if (blockRank == BlockRank.SME) {
                final Location location = blockClicked.getLocation().add(0.5, 1.5, 0.5);
                final String name = ChatColor.of("#FFD100") + TextUtils.toTitleCase(material.toString());
                Item item = GeneralUtils.spawnDisplayItem(itemStack.asQuantity(1), location, name);
                itemStack.setAmount(itemStack.getAmount() - 1);
                BlockStorage.addBlockInfo(blockClicked, PDC_ITEM, item.getUniqueId().toString());
                itemMap.put(blockClicked.getLocation(), item.getUniqueId());
            }
        }
    }
}
