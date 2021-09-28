package io.github.sefiraat.crystamaehistoria.magic.wrappers;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class MagicProjectile {

    @Getter
    private final Projectile projectile;

    public MagicProjectile(EntityType entityType, Location location, Player caster) {
        projectile = (Projectile) location.getWorld().spawnEntity(location, entityType);
        projectile.setShooter(caster);
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
