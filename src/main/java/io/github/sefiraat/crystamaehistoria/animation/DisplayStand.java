package io.github.sefiraat.crystamaehistoria.animation;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class DisplayStand {

    @Getter
    private final ArmorStand armorStand;
    @Getter
    private final long identifier;

    @ParametersAreNonnullByDefault
    public DisplayStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
        this.identifier = getDisplayStandName(armorStand);
    }

    @ParametersAreNonnullByDefault
    public DisplayStand(Block block) {
        long identifier = BlockPosition.getAsLong(block.getLocation());
        ArmorStand stand = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().clone().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
        DisplayStand.setDisplayStandName(stand, identifier);
        setDisplayOnly(stand);
        this.armorStand = stand;
        this.identifier = identifier;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static DisplayStand get(Block block) {
        // Check for an existing stand
        for (Entity e : WorldUtils.getNearbyEntities(block, 2, 2, 2)) {
            if (!(e instanceof ArmorStand)) continue;
            ArmorStand a = (ArmorStand) e;
            if (isDisplayStand(a)) {
                // Found the sucker!
                // TODO Register for removal and tidy
                return new DisplayStand(a);
            }
        }
        return null;
    }

    @ParametersAreNonnullByDefault
    public static void setDisplayStandName(ArmorStand a, long n) {
        PersistentDataAPI.setLong(a, CrystamaeHistoria.getKeys().getPdcArmourStandName(), n);
    }

    @ParametersAreNonnullByDefault
    public static long getDisplayStandName(ArmorStand a) {
        return PersistentDataAPI.getLong(a, CrystamaeHistoria.getKeys().getPdcArmourStandName());
    }

    @ParametersAreNonnullByDefault
    public static void setDisplayStand(ArmorStand a) {
        PersistentDataAPI.setBoolean(a, CrystamaeHistoria.getKeys().getPdcIsDisplayStand(), true);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDisplayStand(ArmorStand a) {
        return PersistentDataAPI.getBoolean(a, CrystamaeHistoria.getKeys().getPdcIsDisplayStand());
    }

    @ParametersAreNonnullByDefault
    public void setDisplayOnly(ArmorStand a) {
        a.setVisible(false);
        a.setGravity(false);
        a.setBasePlate(false);
        a.setCustomNameVisible(false);
        a.setRemoveWhenFarAway(false);
        a.setCollidable(false);
        a.setInvulnerable(true);
        a.setCustomName(ThemeType.getRandomEggName());
        setDisplayStand(a);
    }

    public void kill() {
        this.armorStand.remove();
    }

    @ParametersAreNonnullByDefault
    public void setDisplayItem(Material material) {
        this.armorStand.getEquipment().setHelmet(new ItemStack(material));
    }

    public void clearDisplayItem() {
        this.armorStand.getEquipment().setHelmet(null);
    }

}
