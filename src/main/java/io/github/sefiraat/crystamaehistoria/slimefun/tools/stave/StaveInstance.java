package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import com.google.gson.JsonArray;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class StaveInstance {

    private final ItemStack stave;
    private final Map<SpellStoreLocation, SpellInStaveInstance> spellInStaveInstanceMap = new HashMap<>();

    public StaveInstance(ItemStack stave) {
        this.stave = stave;
    }

    private void save() {
        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<SpellStoreLocation, SpellInStaveInstance> entry : spellInStaveInstanceMap.entrySet()) {
            JsonArray jsonInnerArray = new JsonArray();
            SpellInStaveInstance sp = entry.getValue();
            jsonInnerArray.add(entry.getKey().getSlot());
            jsonInnerArray.add(sp.getSpell().getId());
            jsonInnerArray.add(sp.getCharges());
            jsonInnerArray.add(sp.getCooldown());
            jsonArray.add(jsonInnerArray);
        }
        ItemMeta itemMeta = stave.getItemMeta();
        Validate.notNull(itemMeta, "ItemMeta is null, shouldn't be possible!");
        PersistentDataAPI.setJsonArray(itemMeta, CrystamaeHistoria.getKeyHolder().getPdcStaveInstance(), jsonArray);
    }

}
