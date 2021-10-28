package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AirNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AirSprite;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AncientDefence;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Animaniacs;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AntiPrism;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.BloodMagics;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bobulate;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Break;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bright;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Chaos;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Deity;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EarthNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EndermansVeil;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EtherealFlow;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FlameSprite;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FrostNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Gyroscopic;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HarmonysSonata;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Heal;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HealingMist;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Hellscape;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HolyCow;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.KnowledgeShare;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LavaLake;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LeechBomb;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LovePotion;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Oviparous;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Prism;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Protectorate;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Push;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Quake;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Ravage;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.RemnantOfWar;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Shroud;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.SpawnFiends;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Squall;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.StarFall;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.SummonGolem;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.TimeCompression;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.TimeDilation;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Vacuum;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.WitherWeather;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasinCache;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum SpellType {

    // Tier 1
    AIR_NOVA(new AirNova()),
    AIR_SPRITE(new AirSprite()),
    ANCIENT_DEFENCE(new AncientDefence()),
    ANIMANIACS(new Animaniacs()),
    ANTI_PRISM(new AntiPrism()),
    BLOOD_MAGICS(new BloodMagics()),
    BOBULATE(new Bobulate()),
    BREAK(new Break()),
    BRIGHT(new Bright()),
    CALL_LIGHTNING(new CallLightning()),
    CHAOS(new Chaos()),
    DEITY(new Deity()),
    EARTH_NOVA(new EarthNova()),
    ENDERMANS_VEIL(new EndermansVeil()),
    ETHEREAL_FLOW(new EtherealFlow()),
    FAN_OF_ARROWS(new FanOfArrows()),
    FIREBALL(new Fireball()),
    FIRE_NOVA(new FireNova()),
    FLAME_SPRITE(new FlameSprite()),
    FROST_NOVA(new FrostNova()),
    HEAL(new Heal()),
    HEALING_MIST(new HealingMist()),
    HELLSCAPE(new Hellscape()),
    HOLY_COW(new HolyCow()),
    GYROSCOPIC(new Gyroscopic()),
    HARMONYS_SONATA(new HarmonysSonata()),
    KNOWLEDGE_SHARE(new KnowledgeShare()),
    LAVA_LAKE(new LavaLake()),
    LEECH_BOMB(new LeechBomb()),
    LOVE_POTION(new LovePotion()),
    OVIPAROUS(new Oviparous()),
    POISON_NOVA(new PoisonNova()),
    PRISM(new Prism()),
    PROTECTORATE(new Protectorate()),
    PUSH(new Push()),
    QUAKE(new Quake()),
    RAIN_OF_FIRE(new RainOfFire()),
    RAVAGE(new Ravage()),
    REMNANT_OF_WAR(new RemnantOfWar()),
    SHROUD(new Shroud()),
    SPAWN_FIENDS(new SpawnFiends()),
    SQUALL(new Squall()),
    STAR_FALL(new StarFall()),
    SUMMON_GOLEM(new SummonGolem()),
    TELEPORT(new Teleport()),
    TEMPEST(new Tempest()),
    TIME_COMPRESSION(new TimeCompression()),
    TIME_DILATION(new TimeDilation()),
    VACUUM(new Vacuum()),
    WITHER_WEATHER(new WitherWeather());

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
