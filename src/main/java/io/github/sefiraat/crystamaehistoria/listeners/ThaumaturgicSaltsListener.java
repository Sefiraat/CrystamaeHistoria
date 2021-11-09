package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.ThaumaturgicSalt;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ThaumaturgicSaltsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final ItemStack heldStack = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(heldStack);
        final Block block = e.getClickedBlock();
        if (block != null
            && e.getAction() == Action.RIGHT_CLICK_BLOCK
            && slimefunItem instanceof ThaumaturgicSalt
        ) {
            e.setCancelled(true);
            SlimefunItem item = BlockStorage.check(block);
            if (item instanceof LiquefactionBasin) {
                liquefactionBasin(player, heldStack, item, block);
            }
        }
    }

    private void liquefactionBasin(Player player, ItemStack heldItem, SlimefunItem blockItem, Block clickedBlock) {
        if (GeneralUtils.hasPermission(player, clickedBlock, Interaction.BREAK_BLOCK)) {
            final LiquefactionBasin basin = (LiquefactionBasin) blockItem;
            final LiquefactionBasinCache cache = basin.getCacheMap().get(clickedBlock.getLocation());
            cache.emptyBasin();
            heldItem.setAmount(heldItem.getAmount() - 1);
        }
    }
}
