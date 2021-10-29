package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

/**
 * A {@link PersistentDataType} for {@link InstancePlate}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentPlateDataType implements PersistentDataType<PersistentDataContainer, InstancePlate> {

    public static final PersistentDataType<PersistentDataContainer, InstancePlate> TYPE = new PersistentPlateDataType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<InstancePlate> getComplexType() {
        return InstancePlate.class;
    }

    @Override
    @Nonnull
    public PersistentDataContainer toPrimitive(@Nonnull InstancePlate complex, @Nonnull PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(Keys.PLATE_TIER, PersistentDataType.INTEGER, complex.getTier());
        container.set(Keys.PLATE_SPELL, PersistentDataType.STRING, complex.getStoredSpell().getId());
        container.set(Keys.PLATE_CHARGES, PersistentDataType.INTEGER, complex.getCrysta());
        container.set(Keys.PLATE_COOLDOWN, PersistentDataType.LONG, complex.getCooldown());
        return container;
    }

    @Override
    @Nonnull
    public InstancePlate fromPrimitive(@Nonnull PersistentDataContainer primitive, @Nonnull PersistentDataAdapterContext context) {
        InstancePlate instancePlate = new InstancePlate(
            primitive.get(Keys.PLATE_TIER, PersistentDataType.INTEGER),
            SpellType.valueOf(primitive.get(Keys.PLATE_SPELL, PersistentDataType.STRING)),
            primitive.get(Keys.PLATE_CHARGES, PersistentDataType.INTEGER)
        );
        instancePlate.setCooldown(primitive.get(Keys.PLATE_COOLDOWN, PersistentDataType.LONG));
        return instancePlate;
    }
}
