package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

@Getter
public class StoryPoolProfile {

    public final boolean poolElemental;
    public final boolean poolMechanical;
    public final boolean poolAlchemical;
    public final boolean poolHistorical;
    public final boolean poolHuman;
    public final boolean poolAnimal;
    public final boolean poolCelestial;
    public final boolean poolVoid;
    public final boolean poolPhilosophical;

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
}
