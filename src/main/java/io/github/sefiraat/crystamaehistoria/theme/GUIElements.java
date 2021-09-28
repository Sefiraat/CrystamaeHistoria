package io.github.sefiraat.crystamaehistoria.theme;

import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class GUIElements {

    public static CustomItemStack menuBackground() {
        return new CustomItemStack(
                Material.BLACK_STAINED_GLASS_PANE,
                " ",
                " "
        );
    }

    public static CustomItemStack menuBackgroundInput() {
        return new CustomItemStack(
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                ChatColor.BLUE + "Input",
                " "
        );
    }

    public static CustomItemStack menuWaiting() {
        return new CustomItemStack(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.BLUE + "Please Wait",
                " "
        );
    }

    public static CustomItemStack menuChroniclerPanelNoBlock() {
        return new CustomItemStack(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.BLUE + "No block being chronicled.",
                ThemeUtils.getThemeColor(ThemeType.PASSIVE) + "Place a block above the Chronicler to start."
        );
    }

    public static CustomItemStack menuChroniclerBlockDisplay(Material material) {
        ChatColor colorPassive = ThemeUtils.getThemeColor(ThemeType.PASSIVE);
        return new CustomItemStack(
                material,
                ChatColor.BLUE + "Chronicling Block",
                colorPassive + "This block is being chronicled.",
                colorPassive + "",
                colorPassive + "Stories discovered:"
        );
    }

    public static CustomItemStack menuBackgroundOutput() {
        return new CustomItemStack(
                Material.ORANGE_STAINED_GLASS_PANE,
                ChatColor.RED + "Output",
                " "
        );
    }

    public static CustomItemStack menuBackgroundPreview() {
        return new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                ChatColor.GREEN + "Preview",
                " "
        );
    }

}
