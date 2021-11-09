package io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public class MagicFallingBlock {

    @Getter
    private final UUID fallingBlockUUID;

    @ParametersAreNonnullByDefault
    public MagicFallingBlock(FallingBlock fallingBlock) {
        this.fallingBlockUUID = fallingBlock.getUniqueId();
    }

    /**
     * @param targetLocation The target location
     * @param speed          The speed to push the projectile
     */
    @ParametersAreNonnullByDefault
    public void setVelocity(Location targetLocation, double speed) {
        FallingBlock fallingBlock = (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
        Vector velocity = targetLocation.toVector().subtract(fallingBlock.getLocation().toVector()).normalize();
        setVelocity(velocity, speed);
    }

    /**
     * @param vector The vector to use
     * @param speed  The speed to push the projectile
     */
    @ParametersAreNonnullByDefault
    public void setVelocity(Vector vector, double speed) {
        FallingBlock fallingBlock = (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
        // Fireball projectiles want to move wrongly :)
        if (fallingBlock instanceof Fireball) {
            ((Fireball) fallingBlock).setDirection(vector);
        }
        fallingBlock.setVelocity(vector.multiply(speed));
    }

    @Nonnull
    public Location getLocation() {
        FallingBlock fallingBlock = (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
        return fallingBlock.getLocation();
    }

    @Nullable
    public FallingBlock getFallingBlock() {
        return (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
    }

    public boolean matches(FallingBlock fallingBlock) {
        return this.fallingBlockUUID.equals(fallingBlock.getUniqueId());
    }

    public void disableGravity() {
        FallingBlock fallingBlock = (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
        if (fallingBlock != null) {
            fallingBlock.setGravity(false);
        }
    }

    public void kill() {
        CrystamaeHistoria.getFallingBlockMap().remove(this);
        FallingBlock fallingBlock = (FallingBlock) Bukkit.getEntity(fallingBlockUUID);
        if (fallingBlock != null) {
            fallingBlock.remove();
        }
    }
}
