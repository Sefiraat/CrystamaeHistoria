package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StaveStorage {

    @Getter
    private final Map<SpellSlot, PlateStorage> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    public StaveStorage() {

    }

    public StaveStorage(ItemStack itemStack) {
        Map<SpellSlot, PlateStorage> map = DataTypeMethods.getCustom(
            itemStack.getItemMeta(),
            Keys.PDC_STAVE_STORAGE,
            PersistentStaveDataType.TYPE
        );
        if (map != null) {
            spellInstanceMap.putAll(map);
        }
    }

    public void setSlot(SpellSlot spellSlot, PlateStorage plateStorage) {
        spellInstanceMap.put(spellSlot, plateStorage);
    }

    public CastResult tryCastSpell(SpellSlot slot, CastInformation castInformation) {
        PlateStorage plateStorage = spellInstanceMap.get(slot);
        if (plateStorage != null) {
            return plateStorage.tryCastSpell(castInformation);
        } else {
            return CastResult.CAST_FAIL_SLOT_EMPTY;
        }
    }

    public static void setStaveLore(ItemStack itemStack, StaveStorage staveStorage) {
        final String[] lore = new String[]{
            "A stave with the ability to hold",
            "magically charged plates.",
        };
        final ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        final List<String> finalLore = new ArrayList<>();

        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }

        for (SpellSlot slot : SpellSlot.cashedValues) {
            PlateStorage plateStorage = staveStorage.spellInstanceMap.get(slot);
            if (plateStorage != null) {
                finalLore.add("");
                String magic = ThemeType.toTitleCase(plateStorage.getStoredSpell().getId());
                String crysta = String.valueOf(plateStorage.getCrysta());
                finalLore.add(ThemeType.RARITY_MYTHICAL.getColor() + ThemeType.toTitleCase(slot.name()));
                finalLore.add(ThemeType.PASSIVE.getColor() + "Spell: " + ThemeType.NOTICE.getColor() + magic);
                finalLore.add(ThemeType.PASSIVE.getColor() + "Crysta: " + ThemeType.NOTICE.getColor() + crysta);
            }
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.STAVE.getLoreLine()));
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(finalLore);
        itemStack.setItemMeta(itemMeta);
    }
}
