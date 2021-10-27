package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AncientDefence;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Animaniacs;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AntiPrism;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.BloodMagics;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bobulate;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Break;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bright;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Chaos;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EtherealFlow;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Heal;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HealingMist;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Hellscape;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.KnowledgeShare;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LavaLake;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LovePotion;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Oviparous;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Prism;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Protectorate;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Push;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Quake;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Shroud;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Squall;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.StarFall;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.TimeDilation;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Vacuum;
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
    STAR_FALL(new StarFall()),
    OVIPAROUS(new Oviparous()),
    BLOOD_MAGICS(new BloodMagics()),
    PRISM(new Prism()),
    TIME_DILATION(new TimeDilation()),
    PROTECTORATE(new Protectorate()),
    HELLSCAPE(new Hellscape()),
    ANTI_PRISM(new AntiPrism()),
    ANIMANIACS(new Animaniacs());

    @Getter
    protected static final SpellType[] cachedValues = values();
    @Getter
    private final Spell spell;

    @ParametersAreNonnullByDefault
    SpellType(Spell spell) {
        this.spell = spell;
        LiquefactionBasinCache.addSpellRecipe(this, spell.getRecipe());
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
