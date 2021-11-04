package io.github.sefiraat.crystamaehistoria.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleUtils {

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, Particle particle, double rangeRadius) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, Particle particle, double rangeRadius, int numberOfParticles) {
        displayParticleEffect(entity.getLocation().clone().add(0, 1, 0), particle, rangeRadius, numberOfParticles);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, Particle particle, double rangeRadius) {
        displayParticleEffect(location, particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, Particle particle, double rangeRadius, int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y, z), 1);
        }
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, double rangeRadius, int numberOfParticles, Particle.DustOptions dustOptions) {
        displayParticleEffect(entity.getLocation(), rangeRadius, numberOfParticles, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, double rangeRadius, Particle.DustOptions dustOptions) {
        displayParticleEffect(entity.getLocation(), rangeRadius, 5, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, double rangeRadius, int numberOfParticles, Particle.DustOptions dustOptions) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(x, y, z), 1, dustOptions);
        }
    }

    public static void drawLine(Particle particle, Location start, Location end, double space) {
        drawLine(particle, start, end, space, null);
    }

    public static void drawLine(Particle.DustOptions dustOptions, Location start, Location end, double space) {
        drawLine(Particle.REDSTONE, start, end, space, dustOptions);
    }

    public static void drawLine(Particle particle, Location start, Location end, double space, @Nullable Particle.DustOptions dustOptions) {
        final double distance = start.distance(end);
        double currentPoint = 0;
        final Vector startVector = start.toVector();
        final Vector endVector = end.toVector();
        final Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);

        while (currentPoint < distance) {
            if (dustOptions != null) {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1,
                    dustOptions
                );
            } else {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1
                );
            }
            currentPoint += space;
            startVector.add(vector);
        }
    }

    public static void drawCube(Particle particle, Location corner1, Location corner2, double space) {
        drawCube(particle, corner1, corner2, space, null);
    }

    public static void drawCube(Particle.DustOptions dustOptions, Location corner1, Location corner2, double space) {
        drawCube(Particle.REDSTONE, corner1, corner2, space, dustOptions);
    }

    /**
     * https://www.spigotmc.org/threads/create-particles-in-cube-outline-shape.65991/
     */
    public static void drawCube(Particle particle, Location corner1, Location corner2, double particleDistance, @Nullable Particle.DustOptions dustOptions) {
        World world = corner1.getWorld();
        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (double x = minX; x <= maxX; x += particleDistance) {
            for (double y = minY; y <= maxY; y += particleDistance) {
                for (double z = minZ; z <= maxZ; z += particleDistance) {
                    int components = 0;
                    if (x == minX || x == maxX) components++;
                    if (y == minY || y == maxY) components++;
                    if (z == minZ || z == maxZ) components++;
                    if (components >= 2) {
                        if (dustOptions != null) {
                            world.spawnParticle(particle, x, y, z, 1, dustOptions);
                        } else {
                            world.spawnParticle(particle, x, y, z, 1);
                        }
                    }
                }
            }
        }
    }
}
