package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
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

public class PersistentStaveDataType implements PersistentDataType<PersistentDataContainer[], Map<SpellSlot, InstancePlate>> {

    public static final PersistentDataType<PersistentDataContainer[], Map<SpellSlot, InstancePlate>> TYPE = new PersistentStaveDataType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer[]> getPrimitiveType() {
        return PersistentDataContainer[].class;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public Class<Map<SpellSlot, InstancePlate>> getComplexType() {
        return (Class<Map<SpellSlot, InstancePlate>>) (Class<?>) Map.class;
    }

    @Override
    @Nonnull
    public PersistentDataContainer[] toPrimitive(@Nonnull Map<SpellSlot, InstancePlate> complex, @Nonnull PersistentDataAdapterContext context) {
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;

        for (Map.Entry<SpellSlot, InstancePlate> spellTypeEntry : complex.entrySet()) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(Keys.STAVE_SLOT, PersistentDataType.STRING, spellTypeEntry.getKey().toString());
            container.set(Keys.STAVE_PLATE, PersistentPlateDataType.TYPE, spellTypeEntry.getValue());
            containers[i] = container;
            i++;
        }

        return containers;
    }

    @Override
    @Nonnull
    public Map<SpellSlot, InstancePlate> fromPrimitive(@Nonnull PersistentDataContainer[] primitive, @Nonnull PersistentDataAdapterContext context) {
        Map<SpellSlot, InstancePlate> plateStorageMap = new EnumMap<>(SpellSlot.class);
        for (PersistentDataContainer container : primitive) {
            final SpellSlot spellSlot = SpellSlot.valueOf(container.get(Keys.STAVE_SLOT, PersistentDataType.STRING));
            final InstancePlate instancePlate = container.get(Keys.STAVE_PLATE, PersistentPlateDataType.TYPE);
            plateStorageMap.put(spellSlot, instancePlate);
        }
        return plateStorageMap;
    }
}
