package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import de.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.Waystone;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import scala.concurrent.impl.FutureConvertersImpl;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class RecallingCrystaMatrix extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public RecallingCrystaMatrix(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler((ItemUseHandler) e -> {
            if (e.getPlayer().isSneaking()) {
                setLocation(e);
            } else {
                teleport(e);
            }
        });
    }

    private void setLocation(PlayerRightClickEvent event) {
        Optional<Block> blockOptional = event.getClickedBlock();
        if (blockOptional.isPresent()) {
            Block block = blockOptional.get();
            SlimefunItem slimefunItem = BlockStorage.check(block);
            Location location = block.getLocation();
            if (slimefunItem instanceof Waystone
                && GeneralUtils.hasPermission(event.getPlayer(), location, Interaction.PLACE_BLOCK)
            ) {
                ItemStack itemStack = event.getItem();
                ItemMeta itemMeta = itemStack.getItemMeta();
                PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                container.set(Keys.newKey("location"), DataType.LOCATION, location);
                itemMeta.lore().remove(itemMeta.lore().size() - 1);
                itemMeta.lore().add(Component.text(location.toString())
                    .color(TextColor.color(200, 30,40)));
                itemStack.setItemMeta(itemMeta);
            }
        }
    }

    private void teleport(PlayerRightClickEvent event) {
        ItemStack itemStack = event.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        Location location = container.get(Keys.newKey("location"), DataType.LOCATION);
        Block block = location.getBlock();
        SlimefunItem slimefunItem = BlockStorage.check(block);
        if (slimefunItem instanceof Waystone
            && GeneralUtils.hasPermission(event.getPlayer(), location, Interaction.PLACE_BLOCK)
        ) {
            event.getPlayer().teleportAsync(location.add(1, 1, 1), PlayerTeleportEvent.TeleportCause.PLUGIN);
        } else {
            event.getPlayer().sendActionBar(
                Component.text("Waystone connection isn't functional")
                    .color(TextColor.color(200, 30, 40))
            );
        }
    }



}
