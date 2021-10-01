package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SpellCoreBuilder {

    @Getter
    private final int cooldown;
    @Getter
    private final double range;
    @Getter
    private final int durabilityCost;
    @Getter
    private double damageAmount;
    @Getter
    private double knockbackAmount;
    @Getter
    private double healAmount;
    @Getter
    private double projectileAoeRange;
    @Getter
    private double projectileKnockbackAmount;
    @Getter
    private int numberOfTicks;
    @Getter
    private int tickInterval;
    @Getter
    private boolean cooldownMultiplied;
    @Getter
    private boolean rangeMultiplied;
    @Getter
    private boolean durabilityMultiplied;
    @Getter
    private boolean damageMultiplied;
    @Getter
    private boolean knockbackMultiplied;
    @Getter
    private boolean healMultiplied;
    @Getter
    private boolean projectileAoeMultiplied;
    @Getter
    private boolean projectileKnockbackMultiplied;
    @Getter
    private boolean numberOfTicksMultiplied;
    @Getter
    private boolean tickIntervalMultiplied;
    @Getter
    private int particleNumber = 1;

    @Getter
    private boolean isInstantCast;
    @Getter
    private Consumer<SpellCastInformation> instantCastEvent;

    @Getter
    private boolean isProjectileSpell;
    @Getter
    private Consumer<SpellCastInformation> fireProjectileEvent;
    @Getter
    private Consumer<SpellCastInformation> beforeProjectileHitEvent;
    @Getter
    private Consumer<SpellCastInformation> projectileHitEvent;
    @Getter
    private Consumer<SpellCastInformation> afterProjectileHitEvent;

    @Getter
    private boolean isTickingSpell;
    @Getter
    private Consumer<SpellCastInformation> tickEvent;
    @Getter
    private Consumer<SpellCastInformation> afterAllTicksEvent;

    @Getter
    private boolean isDamagingSpell;
    @Getter
    private boolean isHealingSpell;

    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap = new HashMap<>();
    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap = new HashMap<>();

    public SpellCoreBuilder(int cooldown, boolean cooldownMultiplied, double range, boolean rangeMultiplied, int durabilityCost, boolean durabilityMultiplied) {
        this.cooldown = cooldown;
        this.cooldownMultiplied = cooldownMultiplied;
        this.range = range;
        this.rangeMultiplied = rangeMultiplied;
        this.durabilityCost = durabilityCost;
        this.durabilityMultiplied = durabilityMultiplied;
    }

    public SpellCoreBuilder makeHealingSpell(double healAmount, boolean healMultiplied) {
        this.isHealingSpell = true;
        this.healAmount = healAmount;
        this.healMultiplied = healMultiplied;
        return this;
    }

    public SpellCoreBuilder makeDamagingSpell(double damageAmount, boolean damagePowerMultiplied, double knockbackAmount, boolean knockbackMultiplied) {
        this.isDamagingSpell = true;
        this.damageAmount = damageAmount;
        this.damageMultiplied = damagePowerMultiplied;
        this.knockbackAmount = knockbackAmount;
        this.knockbackMultiplied = knockbackMultiplied;
        return this;
    }

    public SpellCoreBuilder makeInstantSpell(Consumer<SpellCastInformation> instantCastMethod) {
        this.isInstantCast = true;
        this.instantCastEvent = instantCastMethod;
        return this;
    }

    public SpellCoreBuilder makeProjectileSpell(
            Consumer<SpellCastInformation> fireProjectileConsumer,
            Consumer<SpellCastInformation> projectileHitConsumer,
            double projectileAoeRange,
            boolean projectileAoeMultiplied,
            double projectileKnockbackAmount,
            boolean projectileKnockbackMultiplied
    ) {
        this.isProjectileSpell = true;
        this.fireProjectileEvent = fireProjectileConsumer;
        this.projectileHitEvent = projectileHitConsumer;
        this.projectileAoeRange = projectileAoeRange;
        this.projectileAoeMultiplied = projectileAoeMultiplied;
        this.projectileKnockbackAmount = projectileKnockbackAmount;
        this.projectileKnockbackMultiplied = projectileKnockbackMultiplied;
        return this;
    }

    public SpellCoreBuilder addBeforeProjectileHitEvent(Consumer<SpellCastInformation> spellCastInformationConsumer) {
        this.beforeProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    public SpellCoreBuilder addAfterProjectileHitEvent(Consumer<SpellCastInformation> spellCastInformationConsumer) {
        this.afterProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    public SpellCoreBuilder makeTickingSpell(Consumer<SpellCastInformation> spellCastInformationConsumer, int numberOfTicks, boolean ticksMultiplied, int tickInterval, boolean tickIntervalMultiplied) {
        this.tickEvent = spellCastInformationConsumer;
        this.isTickingSpell = true;
        this.numberOfTicks = numberOfTicks;
        this.numberOfTicksMultiplied = ticksMultiplied;
        this.tickInterval = tickInterval;
        this.tickIntervalMultiplied = tickIntervalMultiplied;
        return this;
    }

    public SpellCoreBuilder addAfterTicksEvent(Consumer<SpellCastInformation> spellCastInformationConsumer) {
        this.afterAllTicksEvent = spellCastInformationConsumer;
        return this;
    }

    public SpellCoreBuilder setNumberOfParticles(int particleNumber) {
        this.particleNumber = particleNumber;
        return this;
    }

    public SpellCore build() {
        return new SpellCore(this);
    }

    /**
     *
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification The amplification of the effect. If multiple of the same effects are added, the values are combined.
     * @param duration The duration of the effect. If multiple of the same effects are added, the highest is used.
     */
    public void addPositiveEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        if (positiveEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = positiveEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + amplification);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), duration));
            positiveEffectPairMap.put(potionEffectType, integerPair);
        } else {
            positiveEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
    }

    /**
     *
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification The amplification of the effect. If multiple of the same effects are added, the values are combined.
     * @param duration The duration of the effect. If multiple of the same effects are added, the highest is used.
     */
    public void addNegativeEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        if (negativeEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = negativeEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + amplification);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), duration));
            negativeEffectPairMap.put(potionEffectType, integerPair);
        } else {
            negativeEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
    }



}