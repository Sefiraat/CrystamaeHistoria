package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.Map;

public class StaveInstance {

    private static final String SLOT = "slot";
    private static final String SPELL_ID = "spell_id";
    private static final String CHARGES = "charges";
    private static final String NEXT_CAST = "next_cast";

    private final ItemStack stave;
    private final EnumMap<SpellSlot, SpellInstance> spellInstanceMap = new EnumMap<>(SpellSlot.class);
    private final int staveLevel;

    @ParametersAreNonnullByDefault
    public StaveInstance(ItemStack stave) {
        this.stave = stave;
        ItemMeta itemMeta = stave.getItemMeta();
        Validate.notNull(itemMeta, "ItemMeta is null, shouldn't be possible!");
        JsonArray jsonArray = PersistentDataAPI.getJsonArray(stave.getItemMeta(), CrystamaeHistoria.getKeyHolder().getPdcStaveInstance());
        if (jsonArray != null) {
            for (JsonElement jsonElement : jsonArray) {
                if (jsonElement instanceof JsonObject) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    int slot = jsonObject.get(SLOT).getAsInt();
                    SpellType spell = SpellType.valueOf(jsonObject.get(SPELL_ID).getAsString());
                    int charges = jsonObject.get(CHARGES).getAsInt();
                    long nextCast = jsonObject.get(NEXT_CAST).getAsLong();
                    SpellInstance instance = new SpellInstance(spell, charges, nextCast);
                    spellInstanceMap.put(SpellSlot.getBySlot(slot), instance);
                }
            }
        }
        staveLevel = PersistentDataAPI.getInt(itemMeta, CrystamaeHistoria.getKeyHolder().getPdcStaveLevel(), 1);
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
        PersistentDataAPI.setInt(itemMeta, CrystamaeHistoria.getKeyHolder().getPdcStaveLevel(), staveLevel);
        stave.setItemMeta(itemMeta);
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

    public void tryCastSpell(SpellSlot spellSlot, Player player) {
        if (player != null) {
            SpellInstance spellInstance = spellInstanceMap.get(spellSlot);
            if (spellInstance != null) {
                SpellType spellType = spellInstance.getSpell();
                Validate.notNull(spellType, "SpellType stored by stave is not valid.");
                // TODO check for removed or disabled spells post after being added to stave
                long timeNow = System.currentTimeMillis();
                long nextCast = spellInstance.getNextCast();
                CrystamaeHistoria.logWarning("TN: " + timeNow);
                CrystamaeHistoria.logWarning("NC: " + nextCast);
                if (timeNow > nextCast) {
                    CastInformation castInformation = new CastInformation(player, staveLevel);
                    spellType.getSpell().castSpell(castInformation);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ThemeUtils.getThemeColor(ThemeType.CAST) + "Casting Spell : " + ThemeUtils.getThemeColor(ThemeType.PASSIVE) + TextUtils.toTitleCase(spellType.getId())));
                    long cooldown = spellType.getSpell().getCooldown(castInformation);
                    CrystamaeHistoria.logWarning("CD: " + cooldown);
                    long nextCastTime = System.currentTimeMillis() + (cooldown * 1000L);
                    CrystamaeHistoria.logWarning("NN: " + nextCastTime);
                    spellInstance.setNextCast(nextCastTime);
                    save();
                } else {
                    double timeLeftSec = (double) ((nextCast - timeNow) / 1000L);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ThemeUtils.getThemeColor(ThemeType.WARNING) + "Spell on cooldown : " + timeLeftSec + "s remaining."));
                }
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ThemeUtils.getThemeColor(ThemeType.WARNING) + "No spell in slot : " + spellSlot.name()));
            }
        }
    }

}
