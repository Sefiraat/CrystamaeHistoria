package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
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
    private static final String CRYSTA = "crysta";
    private static final String NEXT_CAST = "next_cast";

    private ItemStack itemStack;
    private Stave stave;
    private final EnumMap<SpellSlot, PlateStorage> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    @ParametersAreNonnullByDefault
    public StaveInstance(Stave stave, ItemStack itemStack) {

    }




}
