package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.sefiraat.crystamaehistoria.slimefun.types.Stand;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ExaltationStand extends Stand {

    public ExaltationStand(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void onRightClick(PlayerRightClickEvent e) {
        final Player player = e.getPlayer();

        Optional<Block> optionalBlock = e.getClickedBlock();

        if (!optionalBlock.isPresent()) {
            return;
        }

        e.cancel();

        final Block blockClicked = optionalBlock.get();
        final UUID currentItemUuid = itemMap.get(blockClicked.getLocation());

        if (currentItemUuid != null) {
            // Stand already has an item, we try to remove this then return;
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
            if (currentItem != null) {
                final ItemStack itemStack = currentItem.getItemStack();
                final HashMap<Integer, ItemStack> rejected = player.getInventory().addItem(itemStack);
                if (!rejected.isEmpty()) {
                    blockClicked.getWorld().dropItem(blockClicked.getLocation().clone().add(0.5, 1.5, 0.5), itemStack);
                }
                currentItem.remove();
            }
            BlockStorage.addBlockInfo(blockClicked, PDC_ITEM, null);
            itemMap.remove(blockClicked.getLocation());
            return;
        }

        final ItemStack itemStack = e.getItem();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (slimefunItem instanceof ExaltedItem) {
            final Location location = blockClicked.getLocation().add(0.5, 1.5, 0.5);
            Item item = GeneralUtils.spawnDisplayItem(itemStack.asQuantity(1), location, "");
            itemStack.setAmount(itemStack.getAmount() - 1);
            BlockStorage.addBlockInfo(blockClicked, PDC_ITEM, item.getUniqueId().toString());
            itemMap.put(blockClicked.getLocation(), item.getUniqueId());
        }
    }

    @Override
    public void afterTick(@Nonnull Item item, @Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        SlimefunItem itemOnStand = SlimefunItem.getByItem(item.getItemStack());
        if (itemOnStand instanceof ExaltedItem) {
            ExaltedItem exaltedItem = (ExaltedItem) itemOnStand;
            exaltedItem.onExalt(exaltedItem, item.getLocation().clone());
        }
    }
}
