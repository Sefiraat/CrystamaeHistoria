package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic.PoseChanger;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.networks.utils.Theme;
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

    private final double stepAmount = 0.01;
    private final NamespacedKey poseKey = Keys.newKey("pose_type");
    private final NamespacedKey changeKey = Keys.newKey("change_Type");

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
    public void onInteractStand(PlayerInteractAtEntityEvent e) {
        final Player player = e.getPlayer();
        final ItemStack heldItem = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(heldItem);

        if (slimefunItem instanceof PoseChanger) {
            final Entity entity = e.getRightClicked();

            e.setCancelled(true);
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand = (ArmorStand) entity;
                if (armorStand.isVisible()
                    && !armorStand.isMarker()
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.INTERACT_ENTITY)
                    && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.ATTACK_ENTITY)
                ) {
                    final ItemMeta itemMeta = heldItem.getItemMeta();
                    final PoseType poseType = PoseType.valueOf(PersistentDataAPI.getString(itemMeta, poseKey, "HEAD"));
                    final ChangeType changeType = ChangeType.valueOf(PersistentDataAPI.getString(itemMeta, changeKey, "RESET"));
                    changePose(armorStand, poseType, changeType, player.isSneaking());
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
                Theme.ERROR.getColor(),
                Theme.CLICK_INFO.getColor(),
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
                Theme.ERROR.getColor(),
                Theme.CLICK_INFO.getColor(),
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
    private void changePose(ArmorStand armorStand, PoseType poseType, ChangeType changeType, boolean negative) {
        EulerAngle eulerAngle = getEulerAngle(armorStand, poseType);

        if (eulerAngle == null) {
            return;
        }

        eulerAngle = getChangedEulerAngle(eulerAngle, changeType, negative);
        setEulerAngle(armorStand, poseType, eulerAngle);
    }

    @Nullable
    private EulerAngle getEulerAngle(@Nonnull ArmorStand armorStand, @Nonnull PoseType poseType) {
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
            default:
                return null;
        }
    }

    private EulerAngle getChangedEulerAngle(@Nonnull EulerAngle eulerAngle, @Nonnull ChangeType changeType, boolean negative) {
        final double amount = negative ? -stepAmount : stepAmount;
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
        BASE_VISIBILITY;

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
