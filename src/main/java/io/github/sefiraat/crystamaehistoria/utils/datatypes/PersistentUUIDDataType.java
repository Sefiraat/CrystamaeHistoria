package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import com.google.common.base.Preconditions;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public final class PersistentUUIDDataType implements PersistentDataType<int[], UUID> {

    public static final PersistentDataType<int[], UUID> TYPE = new PersistentUUIDDataType();

    private PersistentUUIDDataType() {
    }

    @Nonnull
    @Override
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @Nonnull
    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public int[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        return toIntArray(complex);
    }

    @Nonnull
    public static int[] toIntArray(@Nonnull UUID uuid) {
        Preconditions.checkNotNull(uuid, "The provided uuid cannot be null!");

        long mostSig = uuid.getMostSignificantBits();
        long leastSig = uuid.getLeastSignificantBits();
        return new int[]{(int) (mostSig >> 32L), (int) mostSig, (int) (leastSig >> 32L), (int) leastSig};
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public UUID fromPrimitive(int[] primitive, PersistentDataAdapterContext context) {
        return fromIntArray(primitive);
    }

    @Nonnull
    public static UUID fromIntArray(int[] ints) {
        Preconditions.checkNotNull(ints, "The provided integer array cannot be null!");
        Preconditions.checkArgument(ints.length == 4, "The integer array must have a length of 4.");

        return new UUID((long) ints[0] << 32L | ints[1] & 0xFFFFFFFFL, (long) ints[2] << 32L | ints[3] & 0xFFFFFFFFL);
    }
}
