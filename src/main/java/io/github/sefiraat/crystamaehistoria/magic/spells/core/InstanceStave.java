package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class InstanceStave {

    @Getter
    public final ItemStack itemStack;

    @Getter
    private final Map<SpellSlot, InstancePlate> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    public InstanceStave(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
        final Map<SpellSlot, InstancePlate> map = DataTypeMethods.getCustom(
            itemStack.getItemMeta(),
            Keys.PDC_STAVE_STORAGE,
            PersistentStaveDataType.TYPE
        );
        if (map != null) {
            spellInstanceMap.putAll(map);
        }
    }

    public void buildLore() {
        final String[] lore = new String[]{
            "A stave with the ability to hold",
            "magically charged plates.",
        };
        final ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        final List<String> finalLore = new ArrayList<>();

        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }

        for (SpellSlot slot : SpellSlot.getCashedValues()) {
            final InstancePlate instancePlate = this.spellInstanceMap.get(slot);
            if (instancePlate != null) {
                finalLore.add("");
                final String magic = ThemeType.toTitleCase(instancePlate.getStoredSpell().getId());
                final String crysta = String.valueOf(instancePlate.getCrysta());
                finalLore.add(ThemeType.RARITY_MYTHICAL.getColor() + ThemeType.toTitleCase(slot.name()));
                finalLore.add(ThemeType.PASSIVE.getColor() + "Spell: " + ThemeType.NOTICE.getColor() + magic);
                finalLore.add(ThemeType.PASSIVE.getColor() + "Crysta: " + ThemeType.NOTICE.getColor() + crysta);
            }
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.STAVE.getLoreLine()));
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(finalLore);
        this.itemStack.setItemMeta(itemMeta);
    }

    public void setSlot(SpellSlot spellSlot, InstancePlate instancePlate) {
        spellInstanceMap.put(spellSlot, instancePlate);
    }

    public CastResult tryCastSpell(SpellSlot slot, CastInformation castInformation) {
        InstancePlate instancePlate = spellInstanceMap.get(slot);
        if (instancePlate != null) {
            return instancePlate.tryCastSpell(castInformation);
        } else {
            return CastResult.CAST_FAIL_SLOT_EMPTY;
        }
    }
}
