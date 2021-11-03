package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.function.Consumer;

public class CastInformation {

    @Getter
    private final UUID caster;
    @Getter
    private final int staveLevel;
    @Getter
    private final Location castLocation;
    @Getter
    @Setter
    private SpellType spellType;
    @Getter
    @Setter
    private Location damageLocation;
    @Getter
    @Setter
    private LivingEntity mainTarget;
    @Getter
    @Setter
    private Block hitBlock;
    @Getter
    @Setter
    private Block targetedBlockOnCast;
    @Getter
    @Setter
    private BlockFace targetedBlockFaceOnCast;
    @Getter
    @Setter
    private Location projectileLocation;
    @Getter
    @Setter
    private int currentTick = 1;
    @Setter
    private Consumer<CastInformation> beforeProjectileHitEvent;
    @Setter
    private Consumer<CastInformation> projectileHitEvent;
    @Setter
    private Consumer<CastInformation> afterProjectileHitEvent;
    @Setter
    private Consumer<CastInformation> projectileHitBlockEvent;
    @Setter
    private Consumer<CastInformation> tickEvent;
    @Setter
    private Consumer<CastInformation> afterTicksEvent;

    @ParametersAreNonnullByDefault
    public CastInformation(Player caster, int staveLevel) {
        this.caster = caster.getUniqueId();
        this.staveLevel = staveLevel;
        this.castLocation = caster.getLocation().clone();
        this.targetedBlockOnCast = caster.getTargetBlockExact(50);
        this.targetedBlockFaceOnCast = caster.getTargetBlockFace(50);
    }

    public Player getCasterAsPlayer() {
        return Bukkit.getPlayer(this.caster);
    }

    public void runPreAffectEvent() {
        if (beforeProjectileHitEvent != null) {
            beforeProjectileHitEvent.accept(this);
        }
    }

    public void runAffectEvent() {
        if (projectileHitEvent != null) {
            projectileHitEvent.accept(this);
        }
    }

    public void runPostAffectEvent() {
        if (afterProjectileHitEvent != null) {
            afterProjectileHitEvent.accept(this);
        }
    }

    public void runProjectileHitBlockEvent() {
        if (projectileHitBlockEvent != null) {
            projectileHitBlockEvent.accept(this);
        }
    }

    public void runTickEvent() {
        if (tickEvent != null) {
            tickEvent.accept(this);
        }
        this.currentTick++;
    }

    public void runAfterTicksEvent() {
        if (afterTicksEvent != null) {
            afterTicksEvent.accept(this);
        }
    }

}
