package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import de.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic.PoseCloner;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.EulerAngle;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentDataType} for {@link InstancePlate}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentPoseType implements PersistentDataType<PersistentDataContainer, PoseCloner.StoredPose> {

    public static final NamespacedKey HEAD = Keys.newKey("head");
    public static final NamespacedKey BODY = Keys.newKey("body");
    public static final NamespacedKey LEFT_ARM = Keys.newKey("left_arm");
    public static final NamespacedKey RIGHT_ARM = Keys.newKey("right_arm");
    public static final NamespacedKey LEFT_LEG = Keys.newKey("left_leg");
    public static final NamespacedKey RIGHT_LEG = Keys.newKey("right_leg");
    public static final NamespacedKey SMALL = Keys.newKey("small");
    public static final NamespacedKey VISIBLE = Keys.newKey("visible");
    public static final NamespacedKey PLATE = Keys.newKey("plate");
    public static final NamespacedKey ARMS = Keys.newKey("arms");
    public static final NamespacedKey GRAVITY = Keys.newKey("gravity");

    public static final PersistentDataType<PersistentDataContainer, PoseCloner.StoredPose> TYPE = new PersistentPoseType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<PoseCloner.StoredPose> getComplexType() {
        return PoseCloner.StoredPose.class;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer toPrimitive(PoseCloner.StoredPose complex, PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(HEAD, DataType.DOUBLE_ARRAY, new double[]{
            complex.getHead().getX(),
            complex.getHead().getY(),
            complex.getHead().getZ()}
        );
        container.set(BODY, DataType.DOUBLE_ARRAY, new double[]{
            complex.getBody().getX(),
            complex.getBody().getY(),
            complex.getBody().getZ()}
        );
        container.set(LEFT_ARM, DataType.DOUBLE_ARRAY, new double[]{
            complex.getLeftArm().getX(),
            complex.getLeftArm().getY(),
            complex.getLeftArm().getZ()}
        );
        container.set(RIGHT_ARM, DataType.DOUBLE_ARRAY, new double[]{
            complex.getRightArm().getX(),
            complex.getRightArm().getY(),
            complex.getRightArm().getZ()}
        );
        container.set(LEFT_LEG, DataType.DOUBLE_ARRAY, new double[]{
            complex.getLeftLeg().getX(),
            complex.getLeftLeg().getY(),
            complex.getLeftLeg().getZ()}
        );
        container.set(RIGHT_LEG, DataType.DOUBLE_ARRAY, new double[]{
            complex.getRightLeg().getX(),
            complex.getRightLeg().getY(),
            complex.getRightLeg().getZ()}
        );
        container.set(SMALL, DataType.BOOLEAN, complex.isSmall());
        container.set(VISIBLE, DataType.BOOLEAN, complex.isVisible());
        container.set(PLATE, DataType.BOOLEAN, complex.isPlateVisible());
        container.set(ARMS, DataType.BOOLEAN, complex.isArmsVisible());
        container.set(GRAVITY, DataType.BOOLEAN, complex.isHasGravity());
        return container;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PoseCloner.StoredPose fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        double[] head = primitive.get(HEAD, DataType.DOUBLE_ARRAY);
        double[] body = primitive.get(BODY, DataType.DOUBLE_ARRAY);
        double[] leftArm = primitive.get(LEFT_ARM, DataType.DOUBLE_ARRAY);
        double[] rightArm = primitive.get(RIGHT_ARM, DataType.DOUBLE_ARRAY);
        double[] leftLeg = primitive.get(LEFT_LEG, DataType.DOUBLE_ARRAY);
        double[] rightLeg = primitive.get(RIGHT_LEG, DataType.DOUBLE_ARRAY);
        boolean isSmall = primitive.get(SMALL, DataType.BOOLEAN);
        boolean isVisible = primitive.get(VISIBLE, DataType.BOOLEAN);
        boolean plateVisible = primitive.get(PLATE, DataType.BOOLEAN);
        boolean armsVisible = primitive.get(ARMS, DataType.BOOLEAN);
        boolean hasGravity = primitive.get(GRAVITY, DataType.BOOLEAN);
        return new PoseCloner.StoredPose(
            new EulerAngle(head[0], head[1], head[2]),
            new EulerAngle(body[0], body[1], body[2]),
            new EulerAngle(leftArm[0], leftArm[1], leftArm[2]),
            new EulerAngle(rightArm[0], rightArm[1], rightArm[2]),
            new EulerAngle(leftLeg[0], leftLeg[1], leftLeg[2]),
            new EulerAngle(rightLeg[0], rightLeg[1], rightLeg[2]),
            isSmall,
            isVisible,
            plateVisible,
            armsVisible,
            hasGravity
        );
    }
}
