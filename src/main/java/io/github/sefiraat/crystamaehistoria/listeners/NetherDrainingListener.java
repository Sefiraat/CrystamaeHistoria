package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class NetherDrainingListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void portalDraining(EntityPortalEnterEvent e) {
        final Entity entity = e.getEntity();
        if (!(entity instanceof Item)) {
            return;
        }

        final Item item = (Item) entity;
        final ItemStack itemStack = item.getItemStack();

        for (Map.Entry<ItemStack, ItemStack> entry : CrystaRecipeTypes.getDrainingRecipes().entrySet()) {
            final ItemStack recipeStack = entry.getKey();
            if (recipeStack == null || recipeStack.getType() == Material.AIR) {
                continue;
            }
            if (SlimefunUtils.isItemSimilar(recipeStack, itemStack, true, false)) {
                item.setItemStack(entry.getValue().asQuantity(itemStack.getAmount()));
                break;
            }
        }
    }

}
