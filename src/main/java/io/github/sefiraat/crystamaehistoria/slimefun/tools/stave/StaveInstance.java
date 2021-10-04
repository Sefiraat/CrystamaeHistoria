package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class StaveInstance {

    private static final String SLOT = "slot";
    private static final String SPELL_ID = "spell_id";
    private static final String CHARGES = "charges";
    private static final String NEXT_CAST = "next_cast";

    private final ItemStack stave;
    private final EnumMap<SpellSlot, SpellInstance> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    @ParametersAreNonnullByDefault
    public StaveInstance(ItemStack stave) {
        this.stave = stave;
        ItemMeta itemMeta = stave.getItemMeta();
        Validate.notNull(itemMeta, "ItemMeta is null, shouldn't be possible!");
        JsonArray jsonArray = PersistentDataAPI.getJsonArray(stave.getItemMeta(), CrystamaeHistoria.getKeyHolder().getPdcStaveInstance());
        if (jsonArray != null) {
            for (int i = 0; i < 4; i++) {
                if (jsonArray.get(i) instanceof JsonObject) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    int slot = jsonObject.get(SLOT).getAsInt();
                    SpellType spell = SpellType.valueOf(jsonObject.get(SPELL_ID).getAsString());
                    int charges = jsonObject.get(CHARGES).getAsInt();
                    long nextCast = jsonObject.get(NEXT_CAST).getAsLong();
                    SpellInstance instance = new SpellInstance(spell, charges, nextCast);
                    spellInstanceMap.put(SpellSlot.getBySlot(slot), instance);
                }
            }
        }
    }

    private void save() {
        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<SpellSlot, SpellInstance> entry : spellInstanceMap.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            SpellInstance sp = entry.getValue();
            SpellType spell = sp.getSpell();
            jsonObject.addProperty(SLOT, entry.getKey().getSlot());
            jsonObject.addProperty(SPELL_ID, spell != null ? spell.getId() : "null");
            jsonObject.addProperty(CHARGES, sp.getCharges());
            jsonObject.addProperty(NEXT_CAST, sp.getNextCast());
            jsonArray.add(jsonObject);
        }
        ItemMeta itemMeta = stave.getItemMeta();
        Validate.notNull(itemMeta, "ItemMeta is null, shouldn't be possible!");
        PersistentDataAPI.setJsonArray(itemMeta, CrystamaeHistoria.getKeyHolder().getPdcStaveInstance(), jsonArray);
    }

    @ParametersAreNonnullByDefault
    public void setSlot(SpellSlot slot, SpellInstance spellInstance) {
        spellInstanceMap.put(slot, spellInstance);
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SpellInstance getSlot(SpellSlot slot) {
        return spellInstanceMap.get(slot);
    }

}
