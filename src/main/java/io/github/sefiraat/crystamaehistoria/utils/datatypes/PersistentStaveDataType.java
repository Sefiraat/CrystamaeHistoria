package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

/**
 * A {@link PersistentDataType} for {@link Story}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentStaveDataType implements PersistentDataType<PersistentDataContainer[], Map<SpellSlot, PlateStorage>> {

    public static final PersistentDataType<PersistentDataContainer[], Map<SpellSlot, PlateStorage>> TYPE = new PersistentStaveDataType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer[]> getPrimitiveType() {
        return PersistentDataContainer[].class;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public Class<Map<SpellSlot, PlateStorage>> getComplexType() {
        return (Class<Map<SpellSlot, PlateStorage>>) (Class<?>) Map.class;
    }

    @Override
    @Nonnull
    public PersistentDataContainer[] toPrimitive(@Nonnull Map<SpellSlot, PlateStorage> complex, @Nonnull PersistentDataAdapterContext context) {
        Keys keys = CrystamaeHistoria.getKeys();
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;

        for (Map.Entry<SpellSlot, PlateStorage> spellTypeEntry : complex.entrySet()) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(keys.getStaveSlot(), PersistentDataType.STRING, spellTypeEntry.getKey().toString());
            container.set(keys.getStavePlate(), PersistentPlateDataType.TYPE, spellTypeEntry.getValue());
            containers[i] = container;
            i++;
        }

        return containers;
    }

    @Override
    @Nonnull
    public Map<SpellSlot, PlateStorage> fromPrimitive(@Nonnull PersistentDataContainer[] primitive, @Nonnull PersistentDataAdapterContext context) {
        Keys keys = CrystamaeHistoria.getKeys();
        Map<SpellSlot, PlateStorage> plateStorageMap = new EnumMap<>(SpellSlot.class);
        for (PersistentDataContainer container : primitive) {
            final SpellSlot spellSlot = SpellSlot.valueOf(container.get(keys.getStaveSlot(), PersistentDataType.STRING));
            final PlateStorage plateStorage = container.get(keys.getStavePlate(), PersistentPlateDataType.TYPE);
            plateStorageMap.put(spellSlot, plateStorage);
        }
        return plateStorageMap;
    }
}