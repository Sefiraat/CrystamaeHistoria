package io.github.sefiraat.crystamaehistoria.utils.theme;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

@UtilityClass
public class GuiElements {

    public static final CustomItemStack MENU_BACKGROUND = new CustomItemStack(
        Material.BLACK_STAINED_GLASS_PANE,
        " "
    );

    public static final CustomItemStack MENU_BACKGROUND_INPUT = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "Input"
    );

    public static final CustomItemStack MENU_STAVE_INPUT = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "Stave Input"
    );

    public static final CustomItemStack MENU_REMOVE_PLATES = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "Remove Plates"
    );

    public static final CustomItemStack MENU_SAVE_STAVE = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        ChatColor.BLUE + "Save Stave Config"
    );

    public static final CustomItemStack MENU_BACKGROUND_OUTPUT = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        ChatColor.RED + "Output"
    );

    public static final CustomItemStack MENU_DIVIDER = new CustomItemStack(
        Material.LIME_STAINED_GLASS_PANE,
        " "
    );

    public static final CustomItemStack TIER_INDICATOR_1 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_1.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "Tier 1"
    );

    public static final CustomItemStack TIER_INDICATOR_2 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_2.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "Tier 2"
    );

    public static final CustomItemStack TIER_INDICATOR_3 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_3.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "Tier 3"
    );

    public static final CustomItemStack TIER_INDICATOR_4 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_4.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "Tier 4"
    );

    public static final CustomItemStack TIER_INDICATOR_5 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_5.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "Tier 5"
    );

    public static ItemStack getUniqueStoryIcon(Material material) {
        return ThemeType.themedItemStack(
            material,
            ThemeType.RARITY_UNIQUE,
            ThemeType.toTitleCase(material.toString()),
            "This unique story has been",
            "chronicled."
        );
    }

    public static ItemStack getStoryNotUnlockedIcon(Material material) {
        return ThemeType.themedItemStack(
            Material.BARRIER,
            ThemeType.RESEARCH,
            ThemeType.toTitleCase(material.toString()),
            MessageFormat.format("{0}{1}LOCKED", ThemeType.RESEARCH.getColor(), ChatColor.BOLD),
            "This unique story is not yet",
            "unlocked.",
            "Stories are unlocked the first time",
            "you chronicle the required block."
        );
    }

    public static ItemStack getSpellNotUnlockedIcon(String id) {
        return ThemeType.themedItemStack(
            Material.BARRIER,
            ThemeType.RESEARCH,
            ThemeType.toTitleCase(id),
            MessageFormat.format("{0}{1}LOCKED", ThemeType.RESEARCH.getColor(), ChatColor.BOLD),
            "This spell is not yet unlocked.",
            "Spells are unlocked the first time",
            "you charge a plate with it in the",
            "liquefaction basin."
        );
    }

    public static CustomItemStack getSpellSlotPane(SpellSlot spellSlot) {
        return new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            ChatColor.GRAY + "Spell Slot : " + ThemeType.toTitleCase(spellSlot.name())
        );
    }

    public static CustomItemStack getDirectionalSlotPane(BlockFace blockFace, boolean active) {
        Material material = active ? Material.RED_STAINED_GLASS_PANE : Material.GREEN_STAINED_GLASS_PANE;
        return new CustomItemStack(
            material,
            ChatColor.GRAY + "Set direction: " + blockFace.name()
        );
    }
}
