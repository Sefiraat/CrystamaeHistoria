package io.github.sefiraat.crystamaehistoria.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;

public class ParticleUtils {

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

}
