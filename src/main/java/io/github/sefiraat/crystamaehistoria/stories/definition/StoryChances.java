package io.github.sefiraat.crystamaehistoria.stories.definition;

import com.google.common.base.Preconditions;

import javax.annotation.ParametersAreNonnullByDefault;

public class StoryChances {

    private final int basic;
    private final int uncommon;
    private final int rare;
    private final int epic;
    private final int mythical;

    @ParametersAreNonnullByDefault
    public StoryChances(int basic, int uncommon, int rare, int epic, int mythical) {
        boolean validTotal = basic + uncommon + rare + epic + mythical == 100;
        Preconditions.checkArgument(validTotal, "Chances must add up to 100 for a StoryChance");
        this.basic = basic;
        this.uncommon = uncommon;
        this.rare = rare;
        this.epic = epic;
        this.mythical = mythical;
    }

    public int getBasic() {
        return this.basic + getUncommon();
    }

    public int getUncommon() {
        return this.uncommon + getRare();
    }

    public int getRare() {
        return this.rare + getEpic();
    }

    public int getEpic() {
        return this.epic + getMythical();
    }

    public int getMythical() {
        return this.mythical;
    }
}
