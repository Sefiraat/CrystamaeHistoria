package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.CrystamageSatchel;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class SatchelListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInteract(EntityPickupItemEvent e) {
        final Entity entity = e.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }

        final Player player = (Player) entity;
        final Item item = e.getItem();
        final ItemStack itemStack = item.getItemStack();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (slimefunItem instanceof Crystal) {
            final Crystal crystal = (Crystal) slimefunItem;

            for (ItemStack possibleSatchel : player.getInventory().getContents()) {
                final SlimefunItem satchelSfItem = SlimefunItem.getByItem(possibleSatchel);

                // Try to pick up the item into a dank first
                if (satchelSfItem instanceof CrystamageSatchel) {
                    final CrystamageSatchel crystamageSatchel = (CrystamageSatchel) satchelSfItem;
                    if (crystamageSatchel.tryAddItem(possibleSatchel, itemStack, crystal)) {
                        final java.awt.Color baseColor = ThemeType.getByType(crystal.getType()).getColor().getColor();
                        final Color color = Color.fromRGB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());
                        final Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);

                        ParticleUtils.displayParticleEffect(item, 0.4, 10, dustOptions);
                        item.remove();
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
