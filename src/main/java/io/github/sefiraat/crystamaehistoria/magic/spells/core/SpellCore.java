package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SpellCore {

    @Getter
    private final long cooldown;
    @Getter
    private final double range;
    @Getter
    private final int crystaCost;
    @Getter
    private final double damageAmount;
    @Getter
    private final double knockbackAmount;
    @Getter
    private final double healAmount;
    @Getter
    private final double projectileAoeRange;
    @Getter
    private final double projectileKnockbackAmount;
    @Getter
    private final int numberOfTicks;
    @Getter
    private final int tickInterval;
    @Getter
    private final boolean cooldownMultiplied;
    @Getter
    private final boolean rangeMultiplied;
    @Getter
    private final boolean crystaMultiplied;
    @Getter
    private final boolean damageMultiplied;
    @Getter
    private final boolean knockbackMultiplied;
    @Getter
    private final boolean healMultiplied;
    @Getter
    private final boolean projectileAoeMultiplied;
    @Getter
    private final boolean projectileKnockbackMultiplied;
    @Getter
    private final boolean numberOfTicksMultiplied;
    @Getter
    private final boolean tickIntervalMultiplied;
    @Getter
    private final boolean isInstantCast;
    @Getter
    private final Consumer<CastInformation> instantCastEvent;
    @Getter
    private final boolean isProjectileSpell;
    @Getter
    private final Consumer<CastInformation> fireProjectileEvent;
    @Getter
    private final Consumer<CastInformation> beforeProjectileHitEvent;
    @Getter
    private final Consumer<CastInformation> projectileHitEvent;
    @Getter
    private final Consumer<CastInformation> afterProjectileHitEvent;
    @Getter
    private final boolean isTickingSpell;
    @Getter
    private final Consumer<CastInformation> tickEvent;
    @Getter
    private final Consumer<CastInformation> afterAllTicksEvent;
    @Getter
    private final boolean isDamagingSpell;
    @Getter
    private final boolean isHealingSpell;
    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap = new HashMap<>();
    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap = new HashMap<>();
    @Getter
    private int particleNumber = 1;


    @ParametersAreNonnullByDefault
    public SpellCore(SpellCoreBuilder spellCoreBuilder) {
        this.cooldown = spellCoreBuilder.getCooldown();
        this.range = spellCoreBuilder.getRange();
        this.crystaCost = spellCoreBuilder.getCrystaCost();
        this.damageAmount = spellCoreBuilder.getDamageAmount();
        this.knockbackAmount = spellCoreBuilder.getKnockbackAmount();
        this.healAmount = spellCoreBuilder.getHealAmount();
        this.projectileAoeRange = spellCoreBuilder.getProjectileAoeRange();
        this.projectileKnockbackAmount = spellCoreBuilder.getProjectileKnockbackAmount();
        this.numberOfTicks = spellCoreBuilder.getNumberOfTicks();
        this.tickInterval = spellCoreBuilder.getTickInterval();
        this.cooldownMultiplied = spellCoreBuilder.isCooldownMultiplied();
        this.rangeMultiplied = spellCoreBuilder.isRangeMultiplied();
        this.crystaMultiplied = spellCoreBuilder.isDurabilityMultiplied();
        this.damageMultiplied = spellCoreBuilder.isDamageMultiplied();
        this.knockbackMultiplied = spellCoreBuilder.isKnockbackMultiplied();
        this.healMultiplied = spellCoreBuilder.isHealMultiplied();
        this.projectileAoeMultiplied = spellCoreBuilder.isProjectileAoeMultiplied();
        this.projectileKnockbackMultiplied = spellCoreBuilder.isProjectileKnockbackMultiplied();
        this.numberOfTicksMultiplied = spellCoreBuilder.isNumberOfTicksMultiplied();
        this.tickIntervalMultiplied = spellCoreBuilder.isTickIntervalMultiplied();
        this.isInstantCast = spellCoreBuilder.isInstantCast();
        this.instantCastEvent = spellCoreBuilder.getInstantCastEvent();
        this.isProjectileSpell = spellCoreBuilder.isProjectileSpell();
        this.fireProjectileEvent = spellCoreBuilder.getFireProjectileEvent();
        this.beforeProjectileHitEvent = spellCoreBuilder.getBeforeProjectileHitEvent();
        this.projectileHitEvent = spellCoreBuilder.getProjectileHitEvent();
        this.afterProjectileHitEvent = spellCoreBuilder.getAfterProjectileHitEvent();
        this.isTickingSpell = spellCoreBuilder.isTickingSpell();
        this.tickEvent = spellCoreBuilder.getTickEvent();
        this.afterAllTicksEvent = spellCoreBuilder.getAfterAllTicksEvent();
        this.isDamagingSpell = spellCoreBuilder.isDamagingSpell();
        this.isHealingSpell = spellCoreBuilder.isHealingSpell();
    }

}
