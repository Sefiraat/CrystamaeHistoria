package io.github.sefiraat.crystamaehistoria.stories;

import org.apache.commons.lang.Validate;

import javax.annotation.ParametersAreNonnullByDefault;

public class StoryChances {

    public final int basic;
    public final int uncommon;
    public final int rare;
    public final int epic;
    public final int mythical;

    @ParametersAreNonnullByDefault
    public StoryChances(int basic, int uncommon, int rare, int epic, int mythical) {
        boolean validTotal = basic + uncommon + rare + epic + mythical == 100;
        Validate.isTrue(validTotal, "Chances must add up to 100 for a StoryChance");
        this.basic = basic;
        this.uncommon = uncommon;
        this.rare = rare;
        this.epic = epic;
        this.mythical = mythical;
    }
}
