package io.github.sefiraat.crystamaehistoria.utils;

import com.destroystokyo.paper.entity.ai.MobGoals;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import java.util.UUID;

@UtilityClass
public class SpellUtils {

    public static <T extends Mob> T summonTemporaryMob(EntityType entityType, UUID caster, Location location, AbstractGoal<T> goal) {
        T mob = (T) location.getWorld().spawnEntity(location, entityType);
        CrystamaeHistoria.getSummonedEntityMap().put(mob, System.currentTimeMillis() + (60 * 1000));
        DataTypeMethods.setCustom(mob, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE, caster);
        goal.setSelf(mob);
        MobGoals mobGoals = Bukkit.getMobGoals();
        mobGoals.addGoal(mob, 1, goal);
        return mob;
    }

}
