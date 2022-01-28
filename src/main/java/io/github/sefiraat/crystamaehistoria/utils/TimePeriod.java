package io.github.sefiraat.crystamaehistoria.utils;

import lombok.Getter;
import org.bukkit.World;

import javax.annotation.ParametersAreNonnullByDefault;

public enum TimePeriod {
    SUNRISE(23000, 23999),
    DAY(24000, 11999),
    SUNSET(12000, 12999),
    NIGHT(13000, 22999),
    WAKE_UP(24000, 24000),
    BED_TIME_RAIN(12010, 12010),
    BED_TIME_CLEAR(12542, 12542),
    MOON_HIDE(167, 167),
    MOON_SHOW(11834, 11834),
    VILLAGER_WORK(2000, 8999),
    VILLAGER_SOCIALISE(9000, 11999),
    VILLAGER_BED_TIME(12000, 23999),
    SKY_LIGHT_WAX_CLEAR(22331, 23961),
    SKY_LIGHT_WAX_RAIN(22331, 23992),
    SKY_LIGHT_WANE_CLEAR(12040, 13670),
    SKY_LIGHT_WANE_RAIN(12010, 13670),
    MOB_SPAWN_CLEAR(13188, 22812),
    MOB_SPAWN_RAIN(12969, 23031);

    @Getter
    private final long start;
    @Getter
    private final long end;

    TimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @ParametersAreNonnullByDefault
    public static boolean isDay(World world) {
        return isDay(world.getTime());
    }

    public static boolean isDay(long time) {
        return time < 13000 || time > 24000;
    }

    @ParametersAreNonnullByDefault
    public static boolean isNight(World world) {
        return isNight(world.getTime());
    }

    public static boolean isNight(long time) {
        return !isDay(time);
    }

    @ParametersAreNonnullByDefault
    public static boolean isActive(World world, TimePeriod timePeriod) {
        return isActive(world.getTime(), timePeriod);
    }

    @ParametersAreNonnullByDefault
    public static boolean isActive(long time, TimePeriod timePeriod) {
        return time >= timePeriod.getStart() && time <= timePeriod.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean villagersAwake(World world) {
        return villagersAwake(world.getTime());
    }

    public static boolean villagersAwake(long time) {
        return time >= WAKE_UP.getStart() && time <= VILLAGER_BED_TIME.getEnd();
    }

    /**
     * Returns if the moon is still visible in the Sky.
     *
     * @param world The world to check.
     * @return True if the moon is/would be out, false if not or wrong world type.
     */
    @ParametersAreNonnullByDefault
    public static boolean moonOut(World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return moonOut(world.getTime());
        }
        return false;
    }

    /**
     * Returns if the moon is still visible in the Sky during the specified time.
     * This method assumes the world is Overworld.
     *
     * @param time The time to check.
     * @return True if the moon is/would be out
     */
    public static boolean moonOut(long time) {
        return time >= MOON_SHOW.getStart() && time <= MOON_HIDE.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean naturalMobsCanSpawn(World world) {
        long time = world.getTime();
        return world.isClearWeather()
            ? naturalMobsCanSpawn(time, false)
            : naturalMobsCanSpawn(time, true);
    }

    public static boolean naturalMobsCanSpawn(long time, boolean rain) {
        return rain
            ? time >= MOB_SPAWN_RAIN.getStart() && time <= MOB_SPAWN_RAIN.getEnd()
            : time >= MOB_SPAWN_CLEAR.getStart() && time <= MOB_SPAWN_CLEAR.getEnd();
    }

    /**
     * Returns if the given world is dark.
     *
     * @param world The world to check.
     * @return True if past sunset/before sunrise or in a different world.
     */
    @ParametersAreNonnullByDefault
    public static boolean isDark(World world) {
        return !isLight(world);
    }

    /**
     * Returns if the given world is light.
     *
     * @param world The world to check.
     * @return True if past sunrise/before sunset or in a different world.
     */
    @ParametersAreNonnullByDefault
    public static boolean isLight(World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return isDay(world.getTime());
        }
        return false;
    }
}