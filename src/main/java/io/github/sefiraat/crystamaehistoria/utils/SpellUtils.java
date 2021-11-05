package io.github.sefiraat.crystamaehistoria.utils;

import com.destroystokyo.paper.entity.ai.MobGoals;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicSummon;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Projectile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Consumer;

@UtilityClass
public class SpellUtils {

    public static <T extends Mob> MagicSummon summonTemporaryMob(
        EntityType entityType,
        UUID caster,
        Location location,
        @Nullable AbstractGoal<T> goal
    ) {
        return summonTemporaryMob(entityType, caster, location, goal, 30);
    }

    public static <T extends Mob> MagicSummon summonTemporaryMob(
        EntityType entityType,
        UUID caster,
        Location location,
        @Nullable AbstractGoal<T> goal,
        int timeInSeconds
    ) {
        return summonTemporaryMob(entityType, caster, location, goal, timeInSeconds * 1000L, null);
    }

    public static <T extends Mob> MagicSummon summonTemporaryMob(
        EntityType entityType,
        UUID caster,
        Location location,
        @Nullable AbstractGoal<T> goal,
        @Nonnull Consumer<MagicSummon> tickConsumer
    ) {
        return summonTemporaryMob(entityType, caster, location, goal, 30, tickConsumer);
    }

    public static <T extends Mob> MagicSummon summonTemporaryMob(
        EntityType entityType,
        UUID caster,
        Location location,
        @Nullable AbstractGoal<T> goal,
        int timeInSeconds,
        @Nonnull Consumer<MagicSummon> tickConsumer
    ) {
        return summonTemporaryMob(entityType, caster, location, goal, timeInSeconds * 1000L, tickConsumer);
    }

    private static <T extends Mob> MagicSummon summonTemporaryMob(
        EntityType entityType,
        UUID caster,
        Location location,
        @Nullable AbstractGoal<T> goal,
        long duration,
        @Nullable Consumer<MagicSummon> tickConsumer
    ) {
        final T mob = (T) location.getWorld().spawnEntity(location, entityType);
        final MagicSummon magicSummon = new MagicSummon(mob.getUniqueId(), caster);
        final MobGoals mobGoals = Bukkit.getMobGoals();

        if (tickConsumer != null) {
            magicSummon.setTickConsumer(tickConsumer);
        }

        CrystamaeHistoria.getSummonedEntityMap().put(magicSummon, System.currentTimeMillis() + duration);
        DataTypeMethods.setCustom(mob, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE, caster);
        mob.setCustomName(ThemeType.getRandomEggName());

        if (goal == null) {
            mobGoals.removeAllGoals(mob);
        } else {
            goal.setSelf(mob);
            mobGoals.addGoal(mob, 1, goal);
        }

        return magicSummon;
    }

    public static MagicProjectile summonMagicProjectile(
        CastInformation castInformation,
        EntityType entityType,
        Location location
    ) {
        return summonMagicProjectile(castInformation, entityType, location, 5);
    }

    public static MagicProjectile summonMagicProjectile(
        CastInformation castInformation,
        EntityType entityType,
        Location location,
        int timeInSeconds
    ) {
        return summonMagicProjectile(castInformation, entityType, location, timeInSeconds * 1000L, null);
    }

    public static MagicProjectile summonMagicProjectile(
        CastInformation castInformation,
        EntityType entityType,
        Location location,
        @Nonnull Consumer<MagicProjectile> tickConsumer
    ) {
        return summonMagicProjectile(castInformation, entityType, location, 30, tickConsumer);
    }

    public static MagicProjectile summonMagicProjectile(
        CastInformation castInformation,
        EntityType entityType,
        Location location,
        int timeInSeconds,
        @Nonnull Consumer<MagicProjectile> tickConsumer
    ) {
        return summonMagicProjectile(castInformation, entityType, location, timeInSeconds * 1000L, tickConsumer);
    }

    private static MagicProjectile summonMagicProjectile(
        CastInformation castInformation,
        EntityType entityType,
        Location location,
        long duration,
        @Nullable Consumer<MagicProjectile> tickConsumer
    ) {
        final Projectile projectile = (Projectile) location.getWorld().spawnEntity(location, entityType);
        final MagicProjectile magicProjectile = new MagicProjectile(projectile);

        projectile.setShooter(Bukkit.getPlayer(castInformation.getCaster()));
        projectile.setBounce(false);
        if (projectile instanceof Fireball) {
            Fireball fireball = (Fireball) projectile;
            fireball.setIsIncendiary(false);
            fireball.setYield(0f);
        }

        if (tickConsumer != null) {
            magicProjectile.setConsumer(tickConsumer);
        }
        CrystamaeHistoria.getProjectileMap().put(magicProjectile, new Pair<>(castInformation, System.currentTimeMillis() + duration));
        return magicProjectile;
    }

    public static MagicFallingBlock summonMagicFallingBlock(
        CastInformation castInformation,
        Location location,
        Material material
    ) {
        return summonMagicFallingBlock(castInformation, location, material, 30);
    }

    public static MagicFallingBlock summonMagicFallingBlock(
        CastInformation castInformation,
        Location location,
        Material material,
        int timeInSeconds
    ) {
        return summonMagicFallingBlock(castInformation, location, material, timeInSeconds * 1000L);
    }

    private static MagicFallingBlock summonMagicFallingBlock(
        CastInformation castInformation,
        Location location,
        Material material,
        long duration
    ) {
        final FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location, material.createBlockData());
        final MagicFallingBlock magicFallingBlock = new MagicFallingBlock(fallingBlock);

        CrystamaeHistoria.getFallingBlockMap().put(magicFallingBlock, new Pair<>(castInformation, System.currentTimeMillis() + duration));
        return magicFallingBlock;
    }
}
