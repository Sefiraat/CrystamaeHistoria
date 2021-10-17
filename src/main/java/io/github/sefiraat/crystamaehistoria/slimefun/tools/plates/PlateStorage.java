package io.github.sefiraat.crystamaehistoria.slimefun.tools.plates;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlateStorage {

    private final int tier;
    private final SpellType storedSpell;
    @Setter
    private int crysta;

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

    public CastResult tryCastSpell(CastInformation castInformation) {
        castInformation.setSpellType(storedSpell);
        Spell spell = storedSpell.getSpell();
        int crystaCost = spell.getCrystaCost(castInformation);
        if (crysta >= crystaCost) {
            spell.castSpell(castInformation);
            this.crysta -= crystaCost;
            return CastResult.CAST_SUCCESS;
        } else {
            return CastResult.CAST_FAIL_NO_CRYSTA;
        }
    }

    public void addCrysta(int amount) {
        this.crysta += amount;
    }
}
