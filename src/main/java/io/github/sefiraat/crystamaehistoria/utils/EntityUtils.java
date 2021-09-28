package io.github.sefiraat.crystamaehistoria.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;

@UtilityClass
public class EntityUtils {

    public static void push(Entity pushed, Location loc, double force) {
        Vector v = loc.toVector().subtract(pushed.getLocation().toVector()).normalize().multiply(force);
        v.add(new Vector(0, force, 0));
        pushed.setVelocity(v);
    }

    public static void pull(Entity pushed, Location loc, double force) {
        Vector v = pushed.getLocation().toVector().subtract(loc.toVector()).normalize().multiply(force);
        v.add(new Vector(0, force, 0));
        pushed.setVelocity(v);
    }

    public static void damageEntity(LivingEntity livingEntity, LivingEntity caster, int damage) {
        damageEntity(livingEntity, caster, damage, null, 0);
    }

    public static void damageEntity(LivingEntity livingEntity, LivingEntity caster, int damage, @Nullable Location knockbackOrigin, double knockbackForce) {
        livingEntity.damage(damage, caster);
        if (knockbackOrigin != null && knockbackForce > 0) {
            EntityUtils.push(livingEntity, knockbackOrigin, knockbackForce);
        }
    }

}
