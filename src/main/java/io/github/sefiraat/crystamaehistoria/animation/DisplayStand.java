package io.github.sefiraat.crystamaehistoria.animation;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.sefiraat.crystamaehistoria.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class DisplayStand {

    @Getter
    private final ArmorStand armorStand;
    @Getter
    private final String name;

    @Nullable
    public static DisplayStand get(Block block) {
        // Check for an existing stand
        for (Entity e : WorldUtils.getNearbyEntities(block, 2, 2, 2)) {
            if (!(e instanceof ArmorStand)) continue;
            ArmorStand a = (ArmorStand) e;
            if (isDisplayStand(a)) {
                // Found the sucker!
                return new DisplayStand(a);
            }
        }
        return null;
    }

    public static void setDisplayStandName(ArmorStand a, String s) {
        PersistentDataAPI.setString(a, CrystamaeHistoria.inst().getKeyHolder().getPdcArmourStandName(), s);
    }

    @Nullable
    public static String getDisplayStandName(ArmorStand a) {
        return PersistentDataAPI.getString(a, CrystamaeHistoria.inst().getKeyHolder().getPdcArmourStandName());
    }

    public static void setDisplayStand(ArmorStand a) {
        PersistentDataAPI.setBoolean(a, CrystamaeHistoria.inst().getKeyHolder().getPdcIsDisplayStand(), true);
    }

    public static boolean isDisplayStand(ArmorStand a) {
        return PersistentDataAPI.getBoolean(a, CrystamaeHistoria.inst().getKeyHolder().getPdcIsDisplayStand());
    }

    public DisplayStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
        this.name = getDisplayStandName(armorStand);
    }

    public DisplayStand(Block block) {
        String identifier = generateStandName(block);
        ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().clone().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
        DisplayStand.setDisplayStandName(armorStand, identifier);
        setDisplayOnly(armorStand);
        this.armorStand = armorStand;
        this.name = identifier;
    }

    public void setDisplayOnly(ArmorStand a) {
        a.setVisible(false);
        a.setGravity(false);
        a.setBasePlate(false);
        a.setCustomNameVisible(false);
        a.setRemoveWhenFarAway(false);
        a.setCollidable(false);
        a.setInvulnerable(true);
        a.setCustomName(ThemeUtils.getRandomEggName());
        setDisplayStand(a);
    }

    public void kill() {
        this.armorStand.remove();
    }

    public void setDisplayItem(Material material) {
        this.armorStand.getEquipment().setHelmet(new ItemStack(material));
    }

    public void clearDisplayItem() {
        this.armorStand.getEquipment().setHelmet(null);
    }

    private String generateStandName(Block block) {
        // TODO WALSHY SPREEM
        return KeyHolder.PANEL_STAND_PREFIX + block.getX() + "|" + block.getY() + "|" + block.getZ();
    }

}
