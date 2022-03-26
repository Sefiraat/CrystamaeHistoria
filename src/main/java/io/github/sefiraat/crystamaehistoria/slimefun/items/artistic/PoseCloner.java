package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import javax.annotation.Nonnull;

public class PoseCloner extends UnplaceableBlock {

    public PoseCloner(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onBlockPlace());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                e.setCancelled(true);
            }
        };
    }

    public static class StoredPose {
        @Getter
        private final EulerAngle head;
        @Getter
        private final EulerAngle body;
        @Getter
        private final EulerAngle leftArm;
        @Getter
        private final EulerAngle rightArm;
        @Getter
        private final EulerAngle leftLeg;
        @Getter
        private final EulerAngle rightLeg;
        @Getter
        private final boolean isSmall;
        @Getter
        private final boolean isVisible;
        @Getter
        private final boolean isPlateVisible;
        @Getter
        private final boolean armsVisible;
        @Getter
        private final boolean hasGravity;

        public StoredPose(@Nonnull ArmorStand armorStand) {
            this(
                armorStand.getHeadPose(),
                armorStand.getBodyPose(),
                armorStand.getLeftArmPose(),
                armorStand.getRightArmPose(),
                armorStand.getLeftLegPose(),
                armorStand.getRightLegPose(),
                armorStand.isSmall(),
                armorStand.isVisible(),
                armorStand.hasBasePlate(),
                armorStand.hasArms(),
                armorStand.hasGravity()
            );
        }

        public StoredPose(
            EulerAngle head,
            EulerAngle body,
            EulerAngle leftArm,
            EulerAngle rightArm,
            EulerAngle leftLeg,
            EulerAngle rightLeg,
            boolean isSmall,
            boolean isVisible,
            boolean isPlateVisible,
            boolean armsVisible,
            boolean hasGravity
        ) {
            this.head = head;
            this.body = body;
            this.leftArm = leftArm;
            this.rightArm = rightArm;
            this.leftLeg = leftLeg;
            this.rightLeg = rightLeg;
            this.isSmall = isSmall;
            this.isVisible = isVisible;
            this.isPlateVisible = isPlateVisible;
            this.armsVisible = armsVisible;
            this.hasGravity = hasGravity;
        }

        public void applyPose(@Nonnull ArmorStand armorStand) {
            armorStand.setHeadPose(this.head);
            armorStand.setBodyPose(this.body);
            armorStand.setLeftArmPose(this.leftArm);
            armorStand.setRightArmPose(this.rightArm);
            armorStand.setLeftLegPose(this.leftLeg);
            armorStand.setRightLegPose(this.rightLeg);
            armorStand.setSmall(this.isSmall);
            armorStand.setVisible(this.isVisible);
            armorStand.setBasePlate(this.isPlateVisible);
            armorStand.setArms(this.armsVisible);
            armorStand.setGravity(this.hasGravity);
        }
    }
}
