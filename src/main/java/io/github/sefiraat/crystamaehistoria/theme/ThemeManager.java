package io.github.sefiraat.crystamaehistoria.theme;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class ThemeManager {

    public static final ChatColor loreTypeDesc = ChatColor.of("#f0ea4f");

    private final String pluginPrefix;
    @Nullable
    private final String pluginSuffix;
    private final Map<ThemeType, ThemeElement> themeMap = new EnumMap<>(ThemeType.class);

    public ThemeManager() {
        this.pluginPrefix = "[Crystamae Historia]";
        this.pluginSuffix = null;
        setupCoreThemes();
        setupAdditionalThemes();
    }

    private void setupCoreThemes() {
        themeMap.put(ThemeType.SUCCESS, new ThemeElement(ChatColor.GREEN));
        themeMap.put(ThemeType.WARNING, new ThemeElement(ChatColor.YELLOW));
        themeMap.put(ThemeType.ERROR, new ThemeElement(ChatColor.RED));
        themeMap.put(ThemeType.NOTICE, new ThemeElement(ChatColor.WHITE));
        themeMap.put(ThemeType.PASSIVE, new ThemeElement(ChatColor.GRAY));
    }

    private void setupAdditionalThemes() {
        themeMap.put(ThemeType.MAIN, new ThemeElement(ChatColor.of("#21588f"), "Crystamae Historia"));
        themeMap.put(ThemeType.CLICK_INFO, new ThemeElement(ChatColor.of("#e4ed32")));
        themeMap.put(ThemeType.CRAFTING, new ThemeElement(ChatColor.of("#dbcea9"), "Crafting Material"));
        themeMap.put(ThemeType.MACHINE, new ThemeElement(ChatColor.of("#3295a8"), "Machine"));
        themeMap.put(ThemeType.MECHANISM, new ThemeElement(ChatColor.of("#3295a8"), "Mechanism"));
        themeMap.put(ThemeType.DROP, new ThemeElement(ChatColor.of("#bf307f"), "Drop"));
        themeMap.put(ThemeType.BASE, new ThemeElement(ChatColor.of("#9e9e9e"), "Base Resource"));
        themeMap.put(ThemeType.CHEST, new ThemeElement(ChatColor.of("#b89b1c"), "Chest"));
        themeMap.put(ThemeType.MOLTEN_METAL, new ThemeElement(ChatColor.of("#a60a0a"), "Molten Metal"));
        themeMap.put(ThemeType.LIQUID, new ThemeElement(ChatColor.of("#65dbb4"), "Component Material"));
        themeMap.put(ThemeType.CAST, new ThemeElement(ChatColor.of("#ffe138"), "Cast"));
        themeMap.put(ThemeType.PART, new ThemeElement(ChatColor.of("#42c8f5"), "Part"));
        themeMap.put(ThemeType.TOOL, new ThemeElement(ChatColor.of("#c2fc03"), "Tool"));
        themeMap.put(ThemeType.ARMOUR, new ThemeElement(ChatColor.of("#c2fc03"), "Armour"));
        themeMap.put(ThemeType.INFO, new ThemeElement(ChatColor.of("#21588f"), "Info"));
        themeMap.put(ThemeType.MOD, new ThemeElement(ChatColor.of("#bf307f"), "Modification"));
        themeMap.put(ThemeType.PROP, new ThemeElement(ChatColor.of("#bf307f"), "Material Trait"));
        themeMap.put(ThemeType.MULT, new ThemeElement(ChatColor.of("#ba12af"), "Mutliblock"));
        themeMap.put(ThemeType.RTY_COMMON, new ThemeElement(ChatColor.of("#dbdbdb"), "Common"));
        themeMap.put(ThemeType.RTY_UNCOMMON, new ThemeElement(ChatColor.of("#97d16b"), "Uncommon"));
        themeMap.put(ThemeType.RTY_RARE, new ThemeElement(ChatColor.of("#d1db5c"), "Rare"));
        themeMap.put(ThemeType.RTY_EPIC, new ThemeElement(ChatColor.of("#b355d9"), "Epic"));
        themeMap.put(ThemeType.RTY_MYTHICAL, new ThemeElement(ChatColor.of("#c42336"), "Mythical"));
    }

}
