package io.github.sefiraat.crystamaehistoria.stories;

import org.apache.commons.lang.Validate;

import javax.annotation.ParametersAreNonnullByDefault;

public class StoryChances {

    public final int chanceBasic;
    public final int chanceUncommon;
    public final int chanceRare;
    public final int chanceEpic;
    public final int chanceMythical;

    @ParametersAreNonnullByDefault
    public StoryChances(int chanceBasic, int chanceUncommon, int chanceRare, int chanceEpic, int chanceMythical) {
        boolean validTotal = chanceBasic + chanceUncommon + chanceRare + chanceEpic + chanceMythical == 100;
        Validate.isTrue(validTotal, "Chances must add up to 100 for a StoryChance");
        this.chanceBasic = chanceBasic;
        this.chanceUncommon = chanceUncommon;
        this.chanceRare = chanceRare;
        this.chanceEpic = chanceEpic;
        this.chanceMythical = chanceMythical;
    }
}
