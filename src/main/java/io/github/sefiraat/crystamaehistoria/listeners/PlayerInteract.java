package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.StaveInstance;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
        if (slimefunItem instanceof Stave) {
            StaveInstance staveInstance = new StaveInstance(itemStack);
            SpellSlot slot = null;
            Action action = e.getAction();
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                slot = player.isSneaking() ? SpellSlot.SHIFT_LEFT_CLICK : SpellSlot.LEFT_CLICK;
            } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                slot = player.isSneaking() ? SpellSlot.SHIFT_RIGHT_CLICK : SpellSlot.RIGHT_CLICK;
            }
            if (slot != null) {
                staveInstance.tryCastSpell(slot, player);
            }
        }

    }

}