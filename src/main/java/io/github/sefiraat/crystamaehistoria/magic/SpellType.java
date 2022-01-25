package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AbstractVoid;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AirNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AirSprite;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AncientDefence;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Animaniacs;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.AntiPrism;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.BatteringRam;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.BloodMagics;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bobulate;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Break;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Bright;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Cascada;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Chaos;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.ChillWind;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Compass;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.CurificationRitual;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Deity;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EarthNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EasterEgg;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EndermansVeil;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EscapeRope;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.EtherealFlow;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FlameSprite;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.FrostNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Gravity;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.GrowUp;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Gyroscopic;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HarmonysSonata;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HarvestMoon;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Heal;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HealingMist;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Hearthstone;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Hellscape;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.HolyCow;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.ImbueVoid;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.KnowledgeShare;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Launch;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LavaLake;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LeechBomb;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.LovePotion;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Oviparous;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.PhantomsFlight;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.PhilosophersStone;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.PlutosDecent;
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
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.StripMine;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.SummonGolem;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.TimeCompression;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.TimeDilation;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Tracer;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.Vacuum;
import io.github.sefiraat.crystamaehistoria.magic.spells.tier1.WitherWeather;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

public enum SpellType {

    // Tier 1
    ABSTRACT_VOID(new AbstractVoid()),
    AIR_NOVA(new AirNova()),
    AIR_SPRITE(new AirSprite()),
    ANCIENT_DEFENCE(new AncientDefence()),
    ANIMANIACS(new Animaniacs()),
    ANTI_PRISM(new AntiPrism()),
    BATTERING_RAM(new BatteringRam()),
    BLOOD_MAGICS(new BloodMagics()),
    BOBULATE(new Bobulate()),
    BREAK(new Break()),
    BRIGHT(new Bright()),
    CALL_LIGHTNING(new CallLightning()),
    CASCADA(new Cascada()),
    CHAOS(new Chaos()),
    CHILL_WIND(new ChillWind()),
    COMPASS(new Compass()),
    CURIFICATION_RITUAL(new CurificationRitual()),
    DEITY(new Deity()),
    EARTH_NOVA(new EarthNova()),
    EASTER_EGG(new EasterEgg()),
    ENDERMANS_VEIL(new EndermansVeil()),
    ESCAPE_ROPE(new EscapeRope()),
    ETHEREAL_FLOW(new EtherealFlow()),
    FAN_OF_ARROWS(new FanOfArrows()),
    FIREBALL(new Fireball()),
    FIRE_NOVA(new FireNova()),
    FLAME_SPRITE(new FlameSprite()),
    FROST_NOVA(new FrostNova()),
    GRAVITY(new Gravity()),
    GROW_UP(new GrowUp()),
    HEAL(new Heal()),
    HEALING_MIST(new HealingMist()),
    HEARTHSTONE(new Hearthstone()),
    HELLSCAPE(new Hellscape()),
    HOLY_COW(new HolyCow()),
    IMBUE_VOID(new ImbueVoid()),
    GYROSCOPIC(new Gyroscopic()),
    HARMONYS_SONATA(new HarmonysSonata()),
    HARVEST_MOON(new HarvestMoon()),
    KNOWLEDGE_SHARE(new KnowledgeShare()),
    LAUNCH(new Launch()),
    LAVA_LAKE(new LavaLake()),
    LEECH_BOMB(new LeechBomb()),
    LOVE_POTION(new LovePotion()),
    OVIPAROUS(new Oviparous()),
    PHANTOMS_FLIGHT(new PhantomsFlight()),
    PHILOSOPHERS_STONE(new PhilosophersStone()),
    PLUTOS_DESCENT(new PlutosDecent()),
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
    STRIP_MINE(new StripMine()),
    SUMMON_GOLEM(new SummonGolem()),
    TELEPORT(new Teleport()),
    TEMPEST(new Tempest()),
    TIME_COMPRESSION(new TimeCompression()),
    TIME_DILATION(new TimeDilation()),
    TRACER(new Tracer()),
    VACUUM(new Vacuum()),
    WITHER_WEATHER(new WitherWeather());

    @Getter
    private static final SpellType[] cachedValues = values();
    @Getter
    private static SpellType[] enabledSpells;
    @Getter
    private final Spell spell;

    @ParametersAreNonnullByDefault
    SpellType(Spell spell) {
        this.spell = spell;
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

    public static void setupEnabledSpells() {
        enabledSpells = Arrays.stream(values())
            .filter(spellType -> spellType.getSpell().isEnabled())
            .toArray(SpellType[]::new);
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
