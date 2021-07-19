package io.github.sefiraat.crystamaehistoria.theme;

import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class GUIElements {

    public static CustomItem menuBackground() {
        return new CustomItem(
                Material.BLACK_STAINED_GLASS_PANE,
                " ",
                " "
        );
    }

    public static CustomItem menuBackgroundInput() {
        return new CustomItem(
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                ChatColor.BLUE + "Input",
                " "
        );
    }

    public static CustomItem menuWaiting() {
        return new CustomItem(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.BLUE + "Please Wait",
                " "
        );
    }

    public static CustomItem menuChroniclerPanelNoBlock() {
        return new CustomItem(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.BLUE + "No block being chronicled.",
                ThemeUtils.getThemeColor(ThemeType.PASSIVE) + "Place a block above the Chronicler to start."
        );
    }

    public static CustomItem menuChroniclerBlockDisplay(Material material) {
        ChatColor colorPassive = ThemeUtils.getThemeColor(ThemeType.PASSIVE);
        return new CustomItem(
                material,
                ChatColor.BLUE + "Chronicling Block",
                colorPassive + "This block is being chronicled.",
                colorPassive + "",
                colorPassive + "Stories discovered:"
        );
    }

    public static CustomItem menuBackgroundOutput() {
        return new CustomItem(
                Material.ORANGE_STAINED_GLASS_PANE,
                ChatColor.RED + "Output",
                " "
        );
    }

    public static CustomItem menuBackgroundPreview() {
        return new CustomItem(
                Material.LIME_STAINED_GLASS_PANE,
                ChatColor.GREEN + "Preview",
                " "
        );
    }

}
