package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTickRunnable;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public abstract class Spell {

    @Getter
    @Setter
    private SpellCore spellCore;
    @Getter
    @Setter
    private boolean enabled;

    @Nonnull
    public abstract String getId();

    @Nonnull
    public abstract String[] getLore();

    @Nonnull
    public abstract Material getMaterial();

    @Nonnull
    public abstract RecipeSpell getRecipe();

    @Nonnull
    public SlimefunItemStack getThemedStack() {
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        for (String s : getLore()) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, "Spell"));
        SlimefunItemStack stack = new SlimefunItemStack(
            getId(),
            getMaterial(),
            ThemeType.applyThemeToString(ThemeType.SPELL, ThemeType.toTitleCase(getId())),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    @ParametersAreNonnullByDefault
    public void castSpell(CastInformation castInformation) {

        if (spellCore.isInstantCast()) {
            spellCore.getInstantCastEvent().accept(castInformation);
        }

        if (spellCore.isProjectileSpell()) {
            spellCore.getFireProjectileEvent().accept(castInformation);
            if (spellCore.isProjectileVsEntitySpell()) {
                castInformation.setBeforeProjectileHitEvent(spellCore.getBeforeProjectileHitEvent());
                castInformation.setProjectileHitEvent(spellCore.getProjectileHitEvent());
                castInformation.setAfterProjectileHitEvent(spellCore.getAfterProjectileHitEvent());
            }
            if (spellCore.isProjectileVsBlockSpell()) {
                castInformation.setProjectileHitBlockEvent(spellCore.getProjectileHitBlockEvent());
            }
        }

        if (spellCore.isTickingSpell()) {
            registerTicker(castInformation, spellCore.getTickInterval(), spellCore.getNumberOfTicks());
        }
    }

    @ParametersAreNonnullByDefault
    public double getCooldownSeconds(CastInformation castInformation) {
        return spellCore.isCooldownDivided() ? spellCore.getCooldownSeconds() / castInformation.getStaveLevel() : spellCore.getCooldownSeconds();
    }

    @ParametersAreNonnullByDefault
    public double getRange(CastInformation castInformation) {
        return spellCore.isRangeMultiplied() ? spellCore.getRange() * castInformation.getStaveLevel() : spellCore.getRange();
    }

    @ParametersAreNonnullByDefault
    public int getCrystaCost(CastInformation castInformation) {
        return spellCore.isCrystaMultiplied() ? spellCore.getCrystaCost() * (castInformation.getStaveLevel()) : spellCore.getCrystaCost();
    }

    @ParametersAreNonnullByDefault
    public double getDamage(CastInformation castInformation) {
        return spellCore.isDamageMultiplied() ? spellCore.getDamageAmount() * castInformation.getStaveLevel() : spellCore.getDamageAmount();
    }

    @ParametersAreNonnullByDefault
    public double getHealAmount(CastInformation castInformation) {
        return spellCore.isHealMultiplied() ? spellCore.getHealAmount() * castInformation.getStaveLevel() : spellCore.getHealAmount();
    }

    @ParametersAreNonnullByDefault
    public double getKnockback(CastInformation castInformation) {
        return spellCore.isKnockbackMultiplied() ? spellCore.getKnockbackAmount() * castInformation.getStaveLevel() : spellCore.getKnockbackAmount();
    }

    @ParametersAreNonnullByDefault
    public double getProjectileKnockback(CastInformation castInformation) {
        return spellCore.isProjectileKnockbackMultiplied() ? spellCore.getProjectileKnockbackAmount() * castInformation.getStaveLevel() : spellCore.getProjectileKnockbackAmount();
    }

    @ParametersAreNonnullByDefault
    public double getProjectileAoe(CastInformation castInformation) {
        return spellCore.isProjectileAoeMultiplied() ? spellCore.getProjectileAoeRange() * castInformation.getStaveLevel() : spellCore.getProjectileAoeRange();
    }

    /**
     * Sets the last damage cause to Magic and Caster
     *
     * @param damagedEntity   The {@link LivingEntity} that was hit by the spell
     * @param castInformation The {@link CastInformation} containing the caster
     */
    @ParametersAreNonnullByDefault
    protected void setLastDamageToCaster(LivingEntity damagedEntity, CastInformation castInformation) {
        setLastDamageToCaster(damagedEntity, castInformation.getCaster());
    }

    /**
     * Sets the last damage cause to Magic and Caster
     *
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param casterUUID    The {@link LivingEntity} that cast the spell
     */
    @ParametersAreNonnullByDefault
    protected void setLastDamageToCaster(@Nonnull LivingEntity damagedEntity, @Nonnull UUID casterUUID) {
        Player player = Bukkit.getPlayer(casterUUID);
        if (player != null) {
            EntityDamageByEntityEvent e = new EntityDamageByEntityEvent(player, damagedEntity, EntityDamageEvent.DamageCause.MAGIC, 0);
            damagedEntity.setLastDamageCause(e);
        }
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     *
     * @param lightningStrike The {@link LightningStrike} being stored
     * @param castInformation The {@link CastInformation} with the stave information
     */
    @ParametersAreNonnullByDefault
    protected void registerLightningStrike(LightningStrike lightningStrike, CastInformation castInformation) {
        castInformation.setBeforeProjectileHitEvent(spellCore.getBeforeProjectileHitEvent());
        castInformation.setProjectileHitEvent(spellCore.getProjectileHitEvent());
        castInformation.setAfterProjectileHitEvent(spellCore.getAfterProjectileHitEvent());
        Long expiry = System.currentTimeMillis() + 1000;
        CrystamaeHistoria.getSpellMemory().getStrikeMap().put(lightningStrike.getUniqueId(), new Pair<>(castInformation, expiry));
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     *
     * @param castInformation The {@link CastInformation} with the stave information
     * @param tickAmount      The number of times this event should tick before stopping.
     */
    @ParametersAreNonnullByDefault
    protected void registerTicker(CastInformation castInformation, long period, int tickAmount) {
        tickAmount = spellCore.isNumberOfTicksMultiplied() ? tickAmount * castInformation.getStaveLevel() : tickAmount;
        period = spellCore.isTickIntervalMultiplied() ? period * castInformation.getStaveLevel() : period;
        castInformation.setTickEvent(spellCore.getTickEvent());
        castInformation.setAfterTicksEvent(spellCore.getAfterAllTicksEvent());

        final SpellTickRunnable ticker = new SpellTickRunnable(castInformation, tickAmount);
        CrystamaeHistoria.getSpellMemory().getTickingCastables().put(ticker, tickAmount);
        ticker.runTaskTimer(CrystamaeHistoria.getInstance(), 0, period);
    }

    /**
     * Applies all registered positive effects on the selected entity
     *
     * @param livingEntity The {@link LivingEntity} to apply the effects to
     */
    @ParametersAreNonnullByDefault
    protected void applyPositiveEffects(LivingEntity livingEntity, CastInformation castInformation) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : spellCore.getPositiveEffectPairMap().entrySet()) {
            int duration = entry.getValue().getSecondValue();
            int amplification = entry.getValue().getFirstValue();

            duration = spellCore.isEffectDurationMultiplied() ? duration * castInformation.getStaveLevel() : duration;
            amplification = spellCore.isAmplificationMultiplied() ? amplification * castInformation.getStaveLevel() : amplification;

            final PotionEffect potionEffect = new PotionEffect(entry.getKey(), duration, amplification - 1);
            livingEntity.addPotionEffect(potionEffect);
        }
    }

    /**
     * Applies all registered negative effects on the selected entity
     *
     * @param livingEntity The {@link LivingEntity} to apply the effects to
     */
    @ParametersAreNonnullByDefault
    protected void applyNegativeEffects(LivingEntity livingEntity, CastInformation castInformation) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : spellCore.getNegativeEffectPairMap().entrySet()) {
            int duration = entry.getValue().getSecondValue();
            int amplification = entry.getValue().getFirstValue();

            duration = spellCore.isEffectDurationMultiplied() ? duration * castInformation.getStaveLevel() : duration;
            amplification = spellCore.isAmplificationMultiplied() ? amplification * castInformation.getStaveLevel() : amplification;

            final PotionEffect potionEffect = new PotionEffect(entry.getKey(), duration, amplification - 1);
            livingEntity.addPotionEffect(potionEffect);
        }
    }

    /**
     * Gets all targets around the damageLocation.
     * Should only be used in response to projectile events.
     * Does NOT include the main target hit
     *
     * @param castInformation The {@link CastInformation} containing the DamageLocation
     * @return
     */
    @ParametersAreNonnullByDefault
    protected Set<LivingEntity> getTargets(CastInformation castInformation, double range) {
        return getTargets(castInformation, range, false);
    }

    /**
     * Gets all targets around the damageLocation.
     * Should only be used in response to projectile events.
     *
     * @param castInformation The {@link CastInformation} containing the DamageLocation
     * @param includeMain     If the main target should be included in the return set
     * @return
     */
    @ParametersAreNonnullByDefault
    protected Set<LivingEntity> getTargets(CastInformation castInformation, double range, boolean includeMain) {
        Set<LivingEntity> livingEntities = new HashSet<>();
        Location location = castInformation.getDamageLocation();
        if (range > 0) {
            for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster() && (entity != castInformation.getMainTarget() || includeMain)) {
                    livingEntities.add((LivingEntity) entity);
                }
            }
        }
        return livingEntities;
    }
}
