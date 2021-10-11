package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.Bright;
import io.github.sefiraat.crystamaehistoria.magic.spells.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.EtherealFlow;
import io.github.sefiraat.crystamaehistoria.magic.spells.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.Heal;
import io.github.sefiraat.crystamaehistoria.magic.spells.HealingMist;
import io.github.sefiraat.crystamaehistoria.magic.spells.KnowledgeShare;
import io.github.sefiraat.crystamaehistoria.magic.spells.LovePotion;
import io.github.sefiraat.crystamaehistoria.magic.spells.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Push;
import io.github.sefiraat.crystamaehistoria.magic.spells.Quake;
import io.github.sefiraat.crystamaehistoria.magic.spells.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.Shroud;
import io.github.sefiraat.crystamaehistoria.magic.spells.Squall;
import io.github.sefiraat.crystamaehistoria.magic.spells.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.Vacuum;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.SpellRecipe;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum SpellType {

    // Tier 1 BlankPlate Recipes
    LIGHTNING_CALL("LIGHTNING_CALL", new CallLightning(), null),
    FAN_OF_ARROWS("FAN_OF_ARROWS", new FanOfArrows(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.HUMAN)),
    FIREBALL("FIREBALL", new Fireball(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.CELESTIAL)),
    POISON_NOVA("POISON_NOVA", new PoisonNova(), null),
    RAIN_OF_FIRE("RAIN_OF_FIRE", new RainOfFire(), null),
    TELEPORT("TELEPORT", new Teleport(), null),
    TEMPEST("TEMPEST", new Tempest(), null),
    FIRE_NOVA("FIRE_NOVA", new FireNova(), null),
    QUAKE("QUAKE", new Quake(), null),
    BRIGHT("BRIGHT", new Bright(), null),
    SQUALL("SQUALL", new Squall(), null),
    ETHEREAL_FLOW("ETHEREAL_FLOW", new EtherealFlow(), null),
    HEAL("HEAL", new Heal(), null),
    HEALING_MIST("HEALING_MIST", new HealingMist(), null),
    LOVE_POTION("LOVE_POTION", new LovePotion(), null),
    SHROUD("SHROUD", new Shroud(), null),
    PUSH("PUSH", new Push(), null),
    VACUUM("VACUUM", new Vacuum(), null),
    KNOWLEDGE_SHARE("KNOWLEDGE_SHARE", new KnowledgeShare(), null);

    @Getter
    protected static final SpellType[] cachedValues = values();

    @Getter
    private final String id;
    @Getter
    private final Spell spell;
    @Getter
    private final SpellRecipe spellRecipe;

    @ParametersAreNonnullByDefault
    SpellType(String id, Spell spell, @Nullable SpellRecipe spellRecipe) {
        this.id = id;
        this.spell = spell;
        this.spellRecipe = spellRecipe;
        if (spellRecipe != null) {
            LiquefactionBasinCache.addSpellRecipe(this, spellRecipe);
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static Spell getById(String id) {
        for (SpellType spell : values()) {
            if (spell.id.equals(id)) {
                return spell.spell;
            }
        }
        return null;
    }

    @Nonnull
    public Spell get() {
        return spell;
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        this.spell.castSpell(castInformation);
    }

}
