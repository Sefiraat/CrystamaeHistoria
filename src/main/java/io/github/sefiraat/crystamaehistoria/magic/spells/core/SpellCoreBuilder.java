package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SpellCoreBuilder {

    @Getter
    private final int cooldown;
    @Getter
    private final double range;
    @Getter
    private final int crystaCost;
    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap = new HashMap<>();
    @Getter
    private final Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap = new HashMap<>();
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
    private Consumer<CastInformation> instantCastEvent;
    @Getter
    private boolean isProjectileSpell;
    @Getter
    private Consumer<CastInformation> fireProjectileEvent;
    @Getter
    private Consumer<CastInformation> beforeProjectileHitEvent;
    @Getter
    private Consumer<CastInformation> projectileHitEvent;
    @Getter
    private Consumer<CastInformation> afterProjectileHitEvent;
    @Getter
    private boolean isTickingSpell;
    @Getter
    private Consumer<CastInformation> tickEvent;
    @Getter
    private Consumer<CastInformation> afterAllTicksEvent;
    @Getter
    private boolean isDamagingSpell;
    @Getter
    private boolean isHealingSpell;

    public SpellCoreBuilder(int cooldown, boolean cooldownMultiplied, double range, boolean rangeMultiplied, int crystaCost, boolean durabilityMultiplied) {
        this.cooldown = cooldown;
        this.cooldownMultiplied = cooldownMultiplied;
        this.range = range;
        this.rangeMultiplied = rangeMultiplied;
        this.crystaCost = crystaCost;
        this.durabilityMultiplied = durabilityMultiplied;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder makeHealingSpell(double healAmount, boolean healMultiplied) {
        this.isHealingSpell = true;
        this.healAmount = healAmount;
        this.healMultiplied = healMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder makeDamagingSpell(double damageAmount, boolean damagePowerMultiplied, double knockbackAmount, boolean knockbackMultiplied) {
        this.isDamagingSpell = true;
        this.damageAmount = damageAmount;
        this.damageMultiplied = damagePowerMultiplied;
        this.knockbackAmount = knockbackAmount;
        this.knockbackMultiplied = knockbackMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeInstantSpell(Consumer<CastInformation> instantCastMethod) {
        this.isInstantCast = true;
        this.instantCastEvent = instantCastMethod;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeProjectileSpell(
        Consumer<CastInformation> fireProjectileConsumer,
        Consumer<CastInformation> projectileHitConsumer,
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

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addBeforeProjectileHitEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.beforeProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addAfterProjectileHitEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.afterProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeTickingSpell(Consumer<CastInformation> spellCastInformationConsumer, int numberOfTicks, boolean ticksMultiplied, int tickInterval, boolean tickIntervalMultiplied) {
        this.tickEvent = spellCastInformationConsumer;
        this.isTickingSpell = true;
        this.numberOfTicks = numberOfTicks;
        this.numberOfTicksMultiplied = ticksMultiplied;
        this.tickInterval = tickInterval;
        this.tickIntervalMultiplied = tickIntervalMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addAfterTicksEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.afterAllTicksEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder setNumberOfParticles(int particleNumber) {
        this.particleNumber = particleNumber;
        return this;
    }

    /**
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification    The amplification of the effect. If multiple of the same effects are added, the cashedValues are combined.
     * @param duration         The duration of the effect in seconds. If multiple of the same effects are added, the highest is used.
     */
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addPositiveEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        duration = duration * 1000;
        if (positiveEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = positiveEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + duration);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), amplification));
            positiveEffectPairMap.put(potionEffectType, integerPair);
        } else {
            positiveEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
        return this;
    }

    /**
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification    The amplification of the effect. If multiple of the same effects are added, the cashedValues are combined.
     * @param duration         The duration of the effect. If multiple of the same effects are added, the highest is used.
     */
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addNegativeEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        duration = duration * 1000;
        if (negativeEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = negativeEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + duration);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), amplification));
            negativeEffectPairMap.put(potionEffectType, integerPair);
        } else {
            negativeEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
        return this;
    }

    @Nonnull
    public SpellCore build() {
        return new SpellCore(this);
    }
}
