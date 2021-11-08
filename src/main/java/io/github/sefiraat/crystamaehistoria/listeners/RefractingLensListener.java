package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

public class RefractingLensListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
        final Block block = e.getClickedBlock();
        if (block != null
            && e.getAction() == Action.RIGHT_CLICK_BLOCK
            && slimefunItem instanceof RefactingLens
        ) {
            e.setCancelled(true);
            SlimefunItem item = BlockStorage.check(block);
            if (item instanceof LiquefactionBasin) {
                liquefactionBasin(player, item, block);
            } else if (item instanceof ExpCollector) {
                expCollector(player, item, block);
            }
        }
    }

    private void liquefactionBasin(Player player, SlimefunItem blockItem, Block clickedBlock) {
        final LiquefactionBasin basin = (LiquefactionBasin) blockItem;
        final LiquefactionBasinCache cache = basin.getCacheMap().get(clickedBlock.getLocation());
        final Builder component = Component.text();
        boolean first = true;
        for (Map.Entry<StoryType, Integer> entry : cache.getContentMap().entrySet()) {
            if (first) {
                first = false;
            } else {
                component.append(
                    Component.text()
                        .content(" | ")
                        .color(ThemeType.PASSIVE.getComponentColor()));
            }
            component.append(
                Component.text()
                    .content(String.valueOf(entry.getValue()))
                    .color(ThemeType.getByType(entry.getKey()).getComponentColor())
            );
        }
        player.sendActionBar(component.build());
    }

    private void expCollector(Player player, SlimefunItem blockItem, Block clickedBlock) {
        final ExpCollector collector = (ExpCollector) blockItem;
        final int volume = collector.getVolumeMap().get(clickedBlock.getLocation());
        final TextComponent component = Component.text()
            .append(Component.text("Exp Stored: ")
                .color(ThemeType.CLICK_INFO.getComponentColor())
                .decoration(TextDecoration.BOLD, true))
            .append(Component.text(String.valueOf(volume))
                .color(ThemeType.PASSIVE.getComponentColor()))
            .build();
        player.sendActionBar(component);
    }

}
