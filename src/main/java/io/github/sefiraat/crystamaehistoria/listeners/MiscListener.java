package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.LuminescenceScoop;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.covers.BlockVeil;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.events.AutoDisenchantEvent;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MiscListener implements Listener {

    @EventHandler
    public void onPlaceStoriedBlock(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            final Player player = e.getPlayer();
            player.sendMessage(ThemeType.WARNING.getColor() + "This block has been saturated with Crysta and can no longer be placed.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlacerStoriedBlock(BlockPlacerPlaceEvent e) {
        ItemStack itemStack = e.getItemStack();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onShootPaintbrush(EntityShootBowEvent e) {
        ItemStack itemStack = e.getConsumable();
        if (SlimefunItem.getByItem(itemStack) instanceof MagicPaintbrush) {
            e.setCancelled(true);
            final Entity entity = e.getEntity();
            if (entity instanceof Player) {
                entity.sendMessage(ThemeType.WARNING.getColor() + "You can't shoot a Paintbrush!");
            }
        }
    }

    @EventHandler
    public void onTryCraft(CraftItemEvent e) {
        for (ItemStack item : e.getInventory().getMatrix()) {
            if (item != null && item.getType() != Material.AIR) {
                if (StoryUtils.isStoried(item)) {
                    e.setCancelled(true);
                    for (HumanEntity viewer : e.getInventory().getViewers()) {
                        viewer.sendMessage(ThemeType.WARNING.getColor() + "You cannot craft using this!");
                    }
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onTryCraft(AutoDisenchantEvent e) {
        final ItemStack itemStack = e.getItem();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlaceCover(BlockPlaceEvent e) {
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        if (SlimefunItem.getByItem(itemStack) instanceof BlockVeil) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkCooldown(PlayerInteractEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (itemStack.getType() != Material.AIR
            && (event.getAction() == Action.RIGHT_CLICK_AIR
            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            && GeneralUtils.isOnCooldown(itemStack)
        ) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void leaveSleepingBag(PlayerBedLeaveEvent event) {
        final Player player = event.getPlayer();
        final Location location = CrystamaeHistoria.getSpellMemory().getSleepingBags().remove(player.getUniqueId());

        if (location != null) {
            location.getBlock().setType(Material.AIR);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onUseScoop(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
            final SlimefunItem item = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());

            if (item instanceof LuminescenceScoop) {
                LuminescenceScoop scoop = (LuminescenceScoop) item;
                if (scoop.isAdjustable()) {
                    scoop.adjustLight(player);
                }
            }
    }
}