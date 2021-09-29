package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CastDefinition {

    @Getter
    private final Player caster;
    @Getter
    private final int powerMulti;
    @Getter
    private final int cooldownMulti;
    @Getter
    private final int durabilityMulti;

    @Getter
    @Setter
    private int damage;
    @Getter
    @Setter
    private double aoeRange;
    @Getter
    @Setter
    private double knockbackForce;
    @Getter
    @Setter
    private double cooldown;

    @Getter
    private final Location castLocation;
    @Getter
    @Setter
    private Location damageLocation;
    @Getter
    @Setter
    private LivingEntity mainTarget;
    @Getter
    private final Set<LivingEntity> additionalTargets = new HashSet<>();

    @Setter
    private Consumer<CastDefinition> beforeAffectEvent;
    @Setter
    private Consumer<CastDefinition> affectEvent;
    @Setter
    private Consumer<CastDefinition> afterAffectEvent;
    @Setter
    private Consumer<CastDefinition> tickEvent;
    @Setter
    private Consumer<CastDefinition> afterTicksEvent;

    public CastDefinition(Player caster, int powerMulti, int cooldownMulti, int durabilityMulti) {
        this.caster = caster;
        this.powerMulti = powerMulti;
        this.cooldownMulti = cooldownMulti;
        this.durabilityMulti = durabilityMulti;
        this.castLocation = caster.getLocation().clone();
    }

    public void setCastInformation(int damage, double aoeRange, double knockbackForce, int cooldown) {
        this.damage = damage * powerMulti;
        this.aoeRange = aoeRange;
        this.knockbackForce = knockbackForce;
        this.cooldown = cooldown * cooldownMulti;
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

    public Set<LivingEntity> getAllTargets() {
        Set<LivingEntity> livingEntities = new HashSet<>();
        livingEntities.add(mainTarget);
        livingEntities.addAll(additionalTargets);
        return livingEntities;
    }

}
