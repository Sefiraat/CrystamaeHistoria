package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;

public class ConnectingCompass extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public ConnectingCompass(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(onItemUse());
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            final Player player = e.getPlayer();
            final ItemStack itemStack = e.getItem();
            final ItemMeta itemMeta = itemStack.getItemMeta();

            if (!(itemMeta instanceof CompassMeta)) {
                return;
            }

            final CompassMeta compassMeta = (CompassMeta) itemMeta;

            if (player.isSneaking()) {
                player.sendMessage(
                        MessageFormat.format("{0}Type a name for this location in chat.", ChatColor.LIGHT_PURPLE)
                );
                ChatUtils.awaitInput(player, s -> nameAndSet(s, itemStack, player.getEyeLocation()));
            } else {
                if (!compassMeta.hasLodestone()) {
                    return;
                }

                final Location location = player.getEyeLocation();
                final Location pointToLocation = compassMeta.getLodestone();

                if (location.getWorld().equals(pointToLocation.getWorld())) {
                    Vector vector = getVector(location, compassMeta.getLodestone());
                    ParticleUtils.drawLine(
                            Particle.REDSTONE,
                            location,
                            location.clone().add(vector.multiply(5)),
                            0.5,
                            new Particle.DustOptions(Color.RED, 1)
                    );
                }
            }
        };
    }


    @ParametersAreNonnullByDefault
    private void nameAndSet(String s, @Nullable ItemStack itemStack, Location location) {
        if (itemStack != null) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta instanceof CompassMeta) {
                CompassMeta compassMeta = (CompassMeta) itemMeta;
                compassMeta.setLodestone(location);
                compassMeta.setLodestoneTracked(false);
                compassMeta.setDisplayName(ThemeType.TOOL.getColor() + s);
                itemStack.setItemMeta(compassMeta);
            }
        }
    }

    /*
    https://www.spigotmc.org/threads/get-the-direction-from-2-location.263645/
     */
    @ParametersAreNonnullByDefault
    public static Vector getVector(Location a, Location b) {
        double dX = a.getX() - b.getX();
        double dY = a.getY() - b.getY();
        double dZ = a.getZ() - b.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        return new Vector(x, z, y);
    }

}
