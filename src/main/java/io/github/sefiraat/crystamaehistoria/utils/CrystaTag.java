package io.github.sefiraat.crystamaehistoria.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

public enum CrystaTag implements Tag<Material> {

    GLAZED_TERRACOTTA,
    SPAWN_EGGS,
    CONCRETE_BLOCKS;

    @Getter
    protected static final CrystaTag[] cachedValues = values();

    private final NamespacedKey namespacedKey;
    private final Set<Material> materialList = EnumSet.noneOf(Material.class);

    CrystaTag() {
        final String name = this.name().toLowerCase(Locale.ROOT);
        final String fileLocation = "/tags/" + name + ".json";
        final JsonParser parser = new JsonParser();

        try {
            final InputStream stream = CrystamaeHistoria.class.getResourceAsStream(fileLocation);
            final JsonReader reader = new JsonReader(new InputStreamReader(stream));
            final JsonObject object = (JsonObject) parser.parse(reader);

            for (JsonElement element : object.get("values").getAsJsonArray()) {
                final String tagString = element.getAsString();
                final Material material = Material.matchMaterial(tagString);
                if (material != null) {
                    materialList.add(material);
                } else {
                    CrystamaeHistoria.getInstance().getLogger().warning(
                        MessageFormat.format("Error with tag: {0}", tagString)
                    );
                }
            }
        } catch (JsonParseException e) {
            CrystamaeHistoria.getInstance().getLogger().warning(
                MessageFormat.format("Error with tag: {0}", fileLocation)
            );
        }
        namespacedKey = new NamespacedKey(CrystamaeHistoria.getInstance(), name);
    }

    public void setup() {
        // Just here to load the class during onEnable
    }

    @Override
    public boolean isTagged(@Nonnull Material material) {
        return materialList.contains(material);
    }

    @Nonnull
    @Override
    public Set<Material> getValues() {
        return materialList;
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return this.namespacedKey;
    }
}
