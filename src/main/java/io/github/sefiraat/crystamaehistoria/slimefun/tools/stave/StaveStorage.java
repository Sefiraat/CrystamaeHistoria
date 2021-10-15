package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class StaveStorage {

    @Getter
    private final Map<SpellSlot, PlateStorage> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    public StaveStorage() {

    }

    public StaveStorage(ItemStack itemStack) {
        Keys keys = CrystamaeHistoria.getKeys();
        Map<SpellSlot, PlateStorage> map = StoryUtils.getCustom(
            itemStack.getItemMeta(),
            keys.getPdcStaveStorage(),
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
}
