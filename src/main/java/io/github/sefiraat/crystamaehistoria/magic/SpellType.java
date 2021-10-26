package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.AncientDefence;
import io.github.sefiraat.crystamaehistoria.magic.spells.Bobulate;
import io.github.sefiraat.crystamaehistoria.magic.spells.Break;
import io.github.sefiraat.crystamaehistoria.magic.spells.Bright;
import io.github.sefiraat.crystamaehistoria.magic.spells.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.Chaos;
import io.github.sefiraat.crystamaehistoria.magic.spells.EtherealFlow;
import io.github.sefiraat.crystamaehistoria.magic.spells.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.Heal;
import io.github.sefiraat.crystamaehistoria.magic.spells.HealingMist;
import io.github.sefiraat.crystamaehistoria.magic.spells.KnowledgeShare;
import io.github.sefiraat.crystamaehistoria.magic.spells.LavaLake;
import io.github.sefiraat.crystamaehistoria.magic.spells.LovePotion;
import io.github.sefiraat.crystamaehistoria.magic.spells.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Push;
import io.github.sefiraat.crystamaehistoria.magic.spells.Quake;
import io.github.sefiraat.crystamaehistoria.magic.spells.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.Shroud;
import io.github.sefiraat.crystamaehistoria.magic.spells.Squall;
import io.github.sefiraat.crystamaehistoria.magic.spells.StarFall;
import io.github.sefiraat.crystamaehistoria.magic.spells.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.Vacuum;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum SpellType {

    // Tier 1 Plate Recipes
    CALL_LIGHTNING(new CallLightning()),
    FAN_OF_ARROWS(new FanOfArrows()),
    FIREBALL(new Fireball()),
    POISON_NOVA(new PoisonNova()),
    RAIN_OF_FIRE(new RainOfFire()),
    TELEPORT(new Teleport()),
    TEMPEST(new Tempest()),
    FIRE_NOVA(new FireNova()),
    QUAKE(new Quake()),
    BRIGHT(new Bright()),
    SQUALL(new Squall()),
    ETHEREAL_FLOW(new EtherealFlow()),
    HEAL(new Heal()),
    HEALING_MIST(new HealingMist()),
    LOVE_POTION(new LovePotion()),
    SHROUD(new Shroud()),
    PUSH(new Push()),
    VACUUM(new Vacuum()),
    KNOWLEDGE_SHARE(new KnowledgeShare()),
    BREAK(new Break()),
    ANCIENT_DEFENCE(new AncientDefence()),
    LAVA_LAKE(new LavaLake()),
    CHAOS(new Chaos()),
    BOBULATE(new Bobulate()),
    STAR_FALL(new StarFall());

    @Getter
    protected static final SpellType[] cachedValues = values();
    @Getter
    private final Spell spell;

    @ParametersAreNonnullByDefault
    SpellType(Spell spell) {
        final String researchId = "CRYS_SPELL" + spell.getId();

        this.spell = spell;
        LiquefactionBasinCache.addSpellRecipe(this, spell.getRecipe());
        new Research(Keys.newKey(researchId), spell.getId().hashCode(), researchId, 1).register();
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static Spell getById(String id) {
        for (SpellType spellType : getCachedValues()) {
            if (spellType.getId().equals(id)) {
                return spellType.spell;
            }
        }
        return null;
    }

    @Nonnull
    public String getId() {
        return spell.getId();
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
