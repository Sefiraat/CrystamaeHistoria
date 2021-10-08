package io.github.sefiraat.crystamaehistoria.stories;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

/**
 * A {@link PersistentDataType} for {@link Story}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 *
 */

public class PersistentStoryDataType implements PersistentDataType<int[], Stories> {

    public static final PersistentDataType<int[], Stories> TYPE = new PersistentStoryDataType();

    @Override
    public @Nonnull
    Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @Override
    public @Nonnull
    Class<Stories> getComplexType() {
        return Stories.class;
    }

    @Override
    public @Nonnull
    int[] toPrimitive(@Nonnull Stories complex, @Nonnull PersistentDataAdapterContext context) {
        return toIntArray(complex);
    }

    @Override
    public @Nonnull
    Stories fromPrimitive(@Nonnull int[] primitive, @Nonnull PersistentDataAdapterContext context) {
        return fromIntArray(primitive);
    }

    public static @Nonnull
    Stories fromIntArray(@Nonnull int[] ints) {
        Validate.notNull(ints, "The provided integer array cannot be null!");
        Stories stories = new Stories();
        for (int i = 0; i < ints.length - 1; i = i + 3) {
            int[] subInts = ArrayUtils.subarray(ints, i, i + 3);
            stories.getStoryList().add(new Story(subInts));
        }
        return stories;
    }

    public static @Nonnull
    int[] toIntArray(@Nonnull Stories stories) {
        Validate.notNull(stories, "The provided story cannot be null!");
        return stories.toPrimitive();
    }

}
