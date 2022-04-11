package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class FragmentedVoid extends SlimefunItem {

    private static final int[] OUTPUT_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};

    private final int range;

    @ParametersAreNonnullByDefault
    public FragmentedVoid(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(category, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void preRegister() {
        addItemHandler(
            onTick(),
            onBreak()
        );
    }

    private BlockTicker onTick() {
        return new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                final Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
                final Collection<Item> itemsToConsume = location.getWorld().getNearbyEntitiesByType(Item.class, location, 1.25);
                final BlockMenu blockMenu = BlockStorage.getInventory(block);

                for (Item item : itemsToConsume) {
                    if (item.getPickupDelay() <= 0 && !SlimefunUtils.hasNoPickupFlag(item)) {
                        final ItemStack itemStack = item.getItemStack();
                        final Map<Integer, ItemStack> leftovers = blockMenu.toInventory().addItem(itemStack);
                        final Optional<ItemStack> possibleItem = leftovers.values().stream().findFirst();
                        if (possibleItem.isPresent()) {
                            item.setItemStack(possibleItem.get());
                        } else {
                            item.remove();
                        }
                    }
                }

                final Collection<Item> items = location.getWorld().getNearbyEntitiesByType(Item.class, location, FragmentedVoid.this.range);
                for (Item item : items) {
                    if (item.getPickupDelay() <= 0 && !SlimefunUtils.hasNoPickupFlag(item)) {
                        GeneralUtils.pullEntity(location, item, 0.5);
                    }
                }

                for (int i = 0; i < 4; i++) {
                    final double xOffset = ThreadLocalRandom.current().nextDouble(-3, 3);
                    final double yOffset = ThreadLocalRandom.current().nextDouble(-3, 3);
                    final double zOffset = ThreadLocalRandom.current().nextDouble(-3, 3);
                    final Location spawnLocation = location.clone().add(xOffset, yOffset, zOffset);
                    final Vibration vibration = new Vibration(spawnLocation, new Vibration.Destination.BlockDestination(location), 20);

                    location.getWorld().spawnParticle(Particle.VIBRATION, spawnLocation, 1, vibration);
                }
            }
        };
    }

    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                final BlockMenu blockMenu = BlockStorage.getInventory(e.getBlock());
                blockMenu.dropItems(blockMenu.getLocation(), OUTPUT_SLOTS);
            }
        };
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                setSize(9);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return OUTPUT_SLOTS;
                }
                return new int[0];
            }
        };
    }
}
