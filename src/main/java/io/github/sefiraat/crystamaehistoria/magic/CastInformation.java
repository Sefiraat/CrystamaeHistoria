package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
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
    @Setter
    private Consumer<CastInformation> beforeAffectEvent;
    @Setter
    private Consumer<CastInformation> affectEvent;
    @Setter
    private Consumer<CastInformation> afterAffectEvent;
    @Setter
    private Consumer<CastInformation> tickEvent;
    @Setter
    private Consumer<CastInformation> afterTicksEvent;

    @ParametersAreNonnullByDefault
    public CastInformation(Player caster, int staveLevel) {
        this.caster = caster.getUniqueId();
        this.staveLevel = staveLevel;
        this.castLocation = caster.getLocation().clone();
    }

    public void runPreAffectEvent() {
        if (beforeAffectEvent != null) beforeAffectEvent.accept(this);
    }

    public void runAffectEvent() {
        if (affectEvent != null) affectEvent.accept(this);
    }

    public void runPostAffectEvent() {
        if (afterAffectEvent != null) afterAffectEvent.accept(this);
    }

    public void runTickEvent() {
        if (tickEvent != null) tickEvent.accept(this);
    }

    public void runAfterTicksEvent() {
        if (afterTicksEvent != null) afterTicksEvent.accept(this);
    }

}
