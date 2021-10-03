package io.github.sefiraat.crystamaehistoria.magic.wrappers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import java.util.UUID;

public class MagicProjectile {

    @Getter
    private final Projectile projectile;

    public MagicProjectile(EntityType entityType, Location location, UUID caster) {
        projectile = (Projectile) location.getWorld().spawnEntity(location, entityType);
        projectile.setShooter(Bukkit.getPlayer(caster));
        projectile.setBounce(false);
        if (projectile instanceof Fireball) {
            Fireball fireball = (Fireball) projectile;
            fireball.setIsIncendiary(false);
            fireball.setYield(0f);
        }
    }

    /**
     *
     * @param targetLocation The target location
     * @param speed The speed to push the projectile
     */
    public void setVelocity(Location targetLocation, double speed) {
        Vector velocity = targetLocation.toVector().subtract(projectile.getLocation().toVector()).normalize();
        setVelocity(velocity, speed);
    }

    /**
     *
     * @param vector The vector to use
     * @param speed The speed to push the projectile
     */
    public void setVelocity(Vector vector, double speed) {
        // Fireball projectiles want to move wrongly :)
        if (projectile instanceof Fireball) {
            ((Fireball) projectile).setDirection(vector);
        }
        projectile.setVelocity(vector.multiply(speed));
    }

    public Location getLocation() {
        return projectile.getLocation();
    }

}
