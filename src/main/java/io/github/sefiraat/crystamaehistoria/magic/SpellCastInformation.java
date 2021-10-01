package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class SpellCastInformation {

    @Getter
    private final UUID caster;
    @Getter
    private final int powerMulti;
    @Getter
    private final int cooldownMulti;
    @Getter
    private final int durabilityMulti;
    @Getter
    private final Location castLocation;
    @Getter
    @Setter
    private Location damageLocation;
    @Getter
    @Setter
    private LivingEntity mainTarget;
    @Setter
    private Consumer<SpellCastInformation> beforeAffectEvent;
    @Setter
    private Consumer<SpellCastInformation> affectEvent;
    @Setter
    private Consumer<SpellCastInformation> afterAffectEvent;
    @Setter
    private Consumer<SpellCastInformation> tickEvent;
    @Setter
    private Consumer<SpellCastInformation> afterTicksEvent;

    public SpellCastInformation(Player caster, int powerMulti, int cooldownMulti, int durabilityMulti) {
        this.caster = caster.getUniqueId();
        this.powerMulti = powerMulti;
        this.cooldownMulti = cooldownMulti;
        this.durabilityMulti = durabilityMulti;
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
