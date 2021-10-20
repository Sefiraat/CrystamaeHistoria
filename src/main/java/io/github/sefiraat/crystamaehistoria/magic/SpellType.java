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
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum SpellType {

    // Tier 1 Plate Recipes
    LIGHTNING_CALL("LIGHTNING_CALL", new CallLightning(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.MECHANICAL, StoryType.HISTORICAL)),
    FAN_OF_ARROWS("FAN_OF_ARROWS", new FanOfArrows(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.HUMAN)),
    FIREBALL("FIREBALL", new Fireball(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.CELESTIAL)),
    POISON_NOVA("POISON_NOVA", new PoisonNova(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ALCHEMICAL, StoryType.HUMAN, StoryType.ANIMAL)),
    RAIN_OF_FIRE("RAIN_OF_FIRE", new RainOfFire(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.VOID)),
    TELEPORT("TELEPORT", new Teleport(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.HISTORICAL, StoryType.VOID)),
    TEMPEST("TEMPEST", new Tempest(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.MECHANICAL, StoryType.CELESTIAL)),
    FIRE_NOVA("FIRE_NOVA", new FireNova(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ELEMENTAL, StoryType.CELESTIAL, StoryType.VOID)),
    QUAKE("QUAKE", new Quake(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.HISTORICAL, StoryType.HUMAN, StoryType.PHILOSOPHICAL)),
    BRIGHT("BRIGHT", new Bright(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ALCHEMICAL, StoryType.HISTORICAL, StoryType.CELESTIAL)),
    SQUALL("SQUALL", new Squall(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ALCHEMICAL, StoryType.HISTORICAL, StoryType.VOID)),
    ETHEREAL_FLOW("ETHEREAL_FLOW", new EtherealFlow(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.HISTORICAL, StoryType.VOID, StoryType.PHILOSOPHICAL)),
    HEAL("HEAL", new Heal(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.HUMAN, StoryType.CELESTIAL, StoryType.PHILOSOPHICAL)),
    HEALING_MIST("HEALING_MIST", new HealingMist(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.CELESTIAL, StoryType.VOID, StoryType.PHILOSOPHICAL)),
    LOVE_POTION("LOVE_POTION", new LovePotion(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ALCHEMICAL, StoryType.ANIMAL, StoryType.CELESTIAL)),
    SHROUD("SHROUD", new Shroud(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.ALCHEMICAL, StoryType.HUMAN, StoryType.VOID)),
    PUSH("PUSH", new Push(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.MECHANICAL, StoryType.HUMAN, StoryType.CELESTIAL)),
    VACUUM("VACUUM", new Vacuum(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.MECHANICAL, StoryType.HUMAN, StoryType.VOID)),
    KNOWLEDGE_SHARE("KNOWLEDGE_SHARE", new KnowledgeShare(), new SpellRecipe(Materials.INERT_PLATE_T_1, StoryType.HISTORICAL, StoryType.HUMAN, StoryType.CELESTIAL));

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
        LiquefactionBasinCache.addSpellRecipe(this, spellRecipe);
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static Spell getById(String id) {
        for (SpellType spell : getCachedValues()) {
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

    public StoryType getRecipeStory(int index) {
        return spellRecipe.getStoryTypes().get(index);
    }

}
