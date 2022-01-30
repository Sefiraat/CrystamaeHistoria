package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class RefractingLensListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
        final Block block = e.getClickedBlock();
        if (block != null
            && e.getAction() == Action.RIGHT_CLICK_BLOCK
            && slimefunItem instanceof RefactingLens
        ) {
            e.setCancelled(true);
            GeneralUtils.putOnCooldown(player.getInventory().getItemInMainHand(), 3);
            SlimefunItem item = BlockStorage.check(block);
            if (item instanceof LiquefactionBasin) {
                liquefactionBasin(player, item, block);
            } else if (item instanceof ExpCollector) {
                expCollector(item, block);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void liquefactionBasin(Player player, SlimefunItem blockItem, Block clickedBlock) {
        final Location location = clickedBlock.getLocation().add(0.5, 1, 0.5);
        final LiquefactionBasin basin = (LiquefactionBasin) blockItem;
        final Map<StoryType, Integer> cacheMap = basin.getCacheMap().get(clickedBlock.getLocation()).getContentMap();

        final double space = 0.5;
        final int numberToDisplay = cacheMap.size();
        final int mod = (numberToDisplay % 2);
        final double evenOffset = mod == 0 ? space / 2 : 0;

        double xPos = (space * (numberToDisplay - mod));

        for (Map.Entry<StoryType, Integer> entry : cacheMap.entrySet()) {
            final Integer integer = entry.getValue();
            final StoryType storyType = entry.getKey();
            final ItemStack itemStack = Materials.getDummyCrystalMap().get(storyType).getItem().clone();
            final double xOffset = (space * xPos) - evenOffset;

            final Vector pointVector = new Vector(xOffset, 0, 0)
                .rotateAroundY(-(player.getLocation().getYaw() * (Math.PI / 180)));

            final DisplayItem displayItem = new DisplayItem(
                itemStack,
                location.clone().add(pointVector),
                ThemeType.getByType(storyType).getColor() + String.valueOf(integer),
                item -> {
                    Particle.DustOptions dustOptions = ThemeType.getByType(storyType).getDustOptions(1);
                    ParticleUtils.displayParticleEffect(item, 0.3, 4, dustOptions);
                }
            );

            displayItem.registerRemoval(3000);
            xPos--;
        }
    }

    private void expCollector(SlimefunItem blockItem, Block clickedBlock) {
        final ExpCollector collector = (ExpCollector) blockItem;
        final Location location = clickedBlock.getLocation();
        final int volume = collector.getVolumeMap().get(clickedBlock.getLocation());
        final ItemStack itemStack = new ItemStack(Material.EXPERIENCE_BOTTLE);
        final DisplayItem displayItem = new DisplayItem(
            itemStack,
            location.clone().add(0.5, 1, 0.5),
            ChatColor.GREEN + String.valueOf(volume),
            item -> {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GREEN, 1);
                ParticleUtils.displayParticleEffect(item, 0.3, 4, dustOptions);
            }
        );
        displayItem.registerRemoval(3000);
    }

}
