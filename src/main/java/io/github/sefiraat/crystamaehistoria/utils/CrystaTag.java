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
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;

public enum CrystaTag implements Tag<Material> {

    GLAZED_TERRACOTTA,
    CONCRETE_BLOCKS;

    @Getter
    protected static final CrystaTag[] cachedValues = values();

    private final NamespacedKey namespacedKey;
    private final Set<Material> materialList = EnumSet.noneOf(Material.class);

    CrystaTag() {
        final Set<Material> materials = EnumSet.noneOf(Material.class);
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
                materialList.add(material);
            }

        } catch (JsonParseException e) {
            CrystamaeHistoria.log(Level.WARNING, MessageFormat.format("Error with tag: {0}", fileLocation));
        }

        namespacedKey = new NamespacedKey(CrystamaeHistoria.getInstance(), name);
    }


    @Override
    public boolean isTagged(@NotNull Material material) {
        return materialList.contains(material);
    }

    @NotNull
    @Override
    public Set<Material> getValues() {
        return materialList;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return this.namespacedKey;
    }
}
