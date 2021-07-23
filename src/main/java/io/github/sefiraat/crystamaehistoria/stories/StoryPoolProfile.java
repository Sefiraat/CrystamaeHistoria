package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

@Getter
public class StoryPoolProfile {

    public final boolean poolAlchemical;
    public final boolean poolAnimal;
    public final boolean poolCelestial;
    public final boolean poolElemental;
    public final boolean poolHistorical;
    public final boolean poolHuman;
    public final boolean poolMechanical;
    public final boolean poolPhilosophical;
    public final boolean poolVoid;

    public StoryPoolProfile(boolean poolElemental, boolean poolMechanical, boolean poolAlchemical, boolean poolHistorical, boolean poolHuman, boolean poolAnimal, boolean poolCelestial, boolean poolVoid, boolean poolPhilosophical) {
        this.poolElemental = poolElemental;
        this.poolMechanical = poolMechanical;
        this.poolAlchemical = poolAlchemical;
        this.poolHistorical = poolHistorical;
        this.poolHuman = poolHuman;
        this.poolAnimal = poolAnimal;
        this.poolCelestial = poolCelestial;
        this.poolVoid = poolVoid;
        this.poolPhilosophical = poolPhilosophical;
    }

    public int getCount() {
        int count = 0;
        if (poolAlchemical) count++;
        if (poolAnimal) count++;
        if (poolCelestial) count++;
        if (poolElemental) count++;
        if (poolHistorical) count++;
        if (poolHuman) count++;
        if (poolMechanical) count++;
        if (poolPhilosophical) count++;
        if (poolVoid) count++;
        return count;
    }

}
