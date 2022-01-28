package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic.ImbuedStand;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic.PoseChanger;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic.PoseCloner;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPoseType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.List;

public class PoseChangerListener implements Listener {

    private static final String IMBUED_ONLY_MESSAGE = ThemeType.WARNING.getColor() + "This can only be done to an Imbued Armorstand";
    private static final double STEP_AMOUNT = 0.01;

    private final NamespacedKey poseKey = Keys.newKey("pose_type");
    private final NamespacedKey changeKey = Keys.newKey("change_Type");
    private final NamespacedKey clonedPoseKey = Keys.newKey("stored_pose");

    @EventHandler(priority = EventPriority.LOW)
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final ItemStack heldItem = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(heldItem);

        if (slimefunItem instanceof PoseChanger && e.getAction().isLeftClick()) {
            e.setCancelled(true);
            changeMode(player, heldItem, player.isSneaking());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPoseChange(PlayerInteractAtEntityEvent e) {
        final Player player = e.getPlayer();
        final ItemStack heldItem = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(heldItem);

        if (slimefunItem instanceof PoseChanger) {
            final Entity entity = e.getRightClicked();

            e.setCancelled(true);
            if (entity instanceof ArmorStand) {
                final ArmorStand armorStand = (ArmorStand) entity;
                final boolean isImbued = PersistentDataAPI.getBoolean(armorStand, ImbuedStand.KEY);
                if ((armorStand.isVisible() || isImbued)
                    && !armorStand.isMarker()
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.INTERACT_ENTITY)
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.ATTACK_ENTITY)
                ) {
                    final ItemMeta itemMeta = heldItem.getItemMeta();
                    final PoseType poseType = PoseType.valueOf(PersistentDataAPI.getString(itemMeta, poseKey, "HEAD"));
                    final ChangeType changeType = ChangeType.valueOf(PersistentDataAPI.getString(itemMeta, changeKey, "RESET"));

                    changePose(player, armorStand, poseType, changeType, player.isSneaking(), isImbued);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPoseClone(PlayerInteractAtEntityEvent e) {
        final Player player = e.getPlayer();
        final ItemStack heldItem = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(heldItem);

        if (slimefunItem instanceof PoseCloner) {
            final Entity entity = e.getRightClicked();

            e.setCancelled(true);
            if (entity instanceof ArmorStand) {
                final ArmorStand armorStand = (ArmorStand) entity;
                if (!armorStand.isMarker()
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.INTERACT_ENTITY)
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.ATTACK_ENTITY)
                ) {
                    final boolean isImbued = PersistentDataAPI.getBoolean(armorStand, ImbuedStand.KEY);
                    if (!isImbued) {
                        player.sendMessage(ThemeType.WARNING.getColor() + "This can only be done to an Imbued Armorstand");
                        return;
                    }

                    final ItemMeta itemMeta = heldItem.getItemMeta();

                    if (player.isSneaking()) {
                        final PoseCloner.StoredPose pose = DataTypeMethods.getCustom(itemMeta, clonedPoseKey, PersistentPoseType.TYPE);

                        if (pose == null) {
                            player.sendMessage(ThemeType.WARNING.getColor() + "No pose has been stored.");
                            return;
                        }
                        pose.applyPose(armorStand);
                        player.sendActionBar(Component.text(ThemeType.WARNING.getColor() + "Pose Applied"));
                    } else {
                        final PoseCloner.StoredPose pose = new PoseCloner.StoredPose(armorStand);

                        DataTypeMethods.setCustom(itemMeta, clonedPoseKey, PersistentPoseType.TYPE, pose);
                        heldItem.setItemMeta(itemMeta);
                        player.sendActionBar(Component.text(ThemeType.WARNING.getColor() + "Pose Stored"));
                    }
                }
            }
        }
    }

    private void changeMode(@Nonnull Player player, @Nonnull ItemStack heldItem, boolean pose) {
        final ItemMeta itemMeta = heldItem.getItemMeta();
        if (!pose) {
            final PoseType poseType = PoseType.valueOf(PersistentDataAPI.getString(itemMeta, poseKey, "HEAD"));
            final PoseType nextType = poseType.getNext();
            final String message = MessageFormat.format(
                "{0}Pose type: {1}{2}",
                ThemeType.ERROR.getColor(),
                ThemeType.CLICK_INFO.getColor(),
                nextType
            );
            final List<String> lore = itemMeta.getLore();

            lore.set(lore.size() - 4, message);
            itemMeta.setLore(lore);
            PersistentDataAPI.setString(itemMeta, poseKey, nextType.toString());
            player.sendActionBar(Component.text(message));
        } else {
            final ChangeType changeType = ChangeType.valueOf(PersistentDataAPI.getString(itemMeta, changeKey, "RESET"));
            final ChangeType nextType = changeType.getNext();
            final String message = MessageFormat.format(
                "{0}Change type: {1}{2}",
                ThemeType.ERROR.getColor(),
                ThemeType.CLICK_INFO.getColor(),
                nextType
            );
            final List<String> lore = itemMeta.getLore();
            lore.set(lore.size() - 3, message);
            itemMeta.setLore(lore);
            PersistentDataAPI.setString(itemMeta, changeKey, nextType.toString());
            player.sendActionBar(Component.text(message));
        }

        heldItem.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    private void changePose(Player player, ArmorStand armorStand, PoseType poseType, ChangeType changeType, boolean negative, boolean isImbued) {
        EulerAngle eulerAngle = getEulerAngle(player, armorStand, poseType, isImbued);

        if (eulerAngle == null) {
            return;
        }

        eulerAngle = getChangedEulerAngle(eulerAngle, changeType, negative);
        setEulerAngle(armorStand, poseType, eulerAngle);
    }

    @Nullable
    private EulerAngle getEulerAngle(@Nonnull Player player, @Nonnull ArmorStand armorStand, @Nonnull PoseType poseType, boolean isImbued) {
        switch (poseType) {
            case HEAD:
                return armorStand.getHeadPose();
            case BODY:
                return armorStand.getBodyPose();
            case LEFT_ARM:
                return armorStand.getLeftArmPose();
            case RIGHT_ARM:
                return armorStand.getRightArmPose();
            case LEFT_LEG:
                return armorStand.getLeftLegPose();
            case RIGHT_LEG:
                return armorStand.getRightLegPose();
            case ARM_VISIBILITY:
                armorStand.setArms(!armorStand.hasArms());
                return null;
            case BASE_VISIBILITY:
                armorStand.setBasePlate(!armorStand.hasBasePlate());
                return null;
            case STAND_VISIBILITY:
                if (isImbued) {
                    armorStand.setVisible(!armorStand.isVisible());
                } else {
                    player.sendMessage(ThemeType.WARNING.getColor() + "This can only be done to an Imbued Armorstand");
                }
                return null;
            case STAND_SIZE:
                if (isImbued) {
                    armorStand.setSmall(!armorStand.isSmall());
                } else {
                    player.sendMessage(ThemeType.WARNING.getColor() + "This can only be done to an Imbued Armorstand");
                }
                return null;
            case STAND_GRAVITY:
                if (isImbued) {
                    armorStand.setGravity(!armorStand.hasGravity());
                    player.sendActionBar(Component.text("Gravity: " + armorStand.hasGravity()));
                } else {
                    player.sendMessage(ThemeType.WARNING.getColor() + "This can only be done to an Imbued Armorstand");
                }
                return null;
            default:
                return null;
        }
    }

    private EulerAngle getChangedEulerAngle(@Nonnull EulerAngle eulerAngle, @Nonnull ChangeType changeType, boolean negative) {
        final double amount = negative ? -STEP_AMOUNT : STEP_AMOUNT;
        switch (changeType) {
            case RESET:
                return EulerAngle.ZERO;
            case X:
                return eulerAngle.add(amount, 0, 0);
            case Y:
                return eulerAngle.add(0, amount, 0);
            case Z:
                return eulerAngle.add(0, 0, amount);
            default:
                throw new IllegalStateException("Unexpected value: " + changeType);
        }
    }

    private void setEulerAngle(@Nonnull ArmorStand armorStand, @Nonnull PoseType poseType, @Nonnull EulerAngle eulerAngle) {
        switch (poseType) {
            case HEAD:
                armorStand.setHeadPose(eulerAngle);
                break;
            case BODY:
                armorStand.setBodyPose(eulerAngle);
                break;
            case LEFT_ARM:
                armorStand.setLeftArmPose(eulerAngle);
                break;
            case RIGHT_ARM:
                armorStand.setRightArmPose(eulerAngle);
                break;
            case LEFT_LEG:
                armorStand.setLeftLegPose(eulerAngle);
                break;
            case RIGHT_LEG:
                armorStand.setRightLegPose(eulerAngle);
                break;
            default:
                break;
        }
    }

    private enum PoseType {
        HEAD,
        BODY,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG,
        ARM_VISIBILITY,
        BASE_VISIBILITY,
        STAND_VISIBILITY,
        STAND_SIZE,
        STAND_GRAVITY,
        STAND_POSITION;

        private static final PoseType[] CACHED_VALUES = values();

        public PoseType getNext() {
            PoseType nextType = CACHED_VALUES[0];
            for (int i = 0; i < CACHED_VALUES.length; i++) {
                if (CACHED_VALUES[i] == this) {
                    nextType = i == CACHED_VALUES.length - 1 ? CACHED_VALUES[0] : CACHED_VALUES[i + 1];
                }
            }
            return nextType;
        }
    }

    private enum ChangeType {
        RESET,
        X,
        Y,
        Z;

        private static final ChangeType[] CACHED_VALUES = values();

        public ChangeType getNext() {
            ChangeType nextType = CACHED_VALUES[0];
            for (int i = 0; i < CACHED_VALUES.length; i++) {
                if (CACHED_VALUES[i] == this) {
                    nextType = i == CACHED_VALUES.length - 1 ? CACHED_VALUES[0] : CACHED_VALUES[i + 1];
                }
            }
            return nextType;
        }
    }
}
