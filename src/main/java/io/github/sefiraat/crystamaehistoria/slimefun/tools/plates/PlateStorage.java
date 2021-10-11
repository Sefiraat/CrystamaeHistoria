package io.github.sefiraat.crystamaehistoria.slimefun.tools.plates;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlateStorage {

    private final int tier;
    private final SpellType storedSpell;
    private final int crysta;

    public PlateStorage(int tier, SpellType storedSpell, int crysta) {
        this.tier = tier;
        this.storedSpell = storedSpell;
        this.crysta = crysta;
    }

    public static void setPlateLore(ItemStack itemStack, PlateStorage plateStorage) {

        String magic = plateStorage != null ? ThemeType.toTitleCase(plateStorage.storedSpell.getId()) : "None";
        String crysta = plateStorage != null ? String.valueOf(plateStorage.crysta) : "0";

        final String[] lore = new String[]{
            "A magically charged plate storing magic",
            "potential.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Magic Framework : " + ThemeType.NOTICE.getColor() + magic,
            ThemeType.CLICK_INFO.getColor() + "Stored Crysta : " + ThemeType.NOTICE.getColor() + crysta
        };
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();

        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.CRAFTING.getLoreLine()));

        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(finalLore);
        itemStack.setItemMeta(itemMeta);
    }

}
