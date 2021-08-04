package io.github.sefiraat.crystamaehistoria.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.List;

@UtilityClass
public class WorldUtils {

    public static List<Entity> getNearbyEntities(Block block, double x, double y, double z) {
        return (List<Entity>) block.getWorld().getNearbyEntities(block.getLocation(), x, y, z);
    }

}
