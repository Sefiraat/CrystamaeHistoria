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
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum SpellType {

    LIGHTNING_CALL("LIGHTNING_CALL", new CallLightning()),
    FAN_OF_ARROWS("FAN_OF_ARROWS", new FanOfArrows()),
    FIREBALL("FIREBALL", new Fireball()),
    POISON_NOVA("POISON_NOVA", new PoisonNova()),
    RAIN_OF_FIRE("RAIN_OF_FIRE", new RainOfFire()),
    TELEPORT("TELEPORT", new Teleport()),
    TEMPEST("TEMPEST", new Tempest()),
    FIRE_NOVA("FIRE_NOVA", new FireNova()),
    QUAKE("QUAKE", new Quake()),
    BRIGHT("BRIGHT", new Bright()),
    SQUALL("SQUALL", new Squall()),
    ETHEREAL_FLOW("ETHEREAL_FLOW", new EtherealFlow()),
    HEAL("HEAL", new Heal()),
    HEALING_MIST("HEALING_MIST", new HealingMist()),
    LOVE_POTION("LOVE_POTION", new LovePotion()),
    SHROUD("SHROUD", new Shroud()),
    PUSH("PUSH", new Push()),
    VACUUM("VACUUM", new Vacuum()),
    KNOWLEDGE_SHARE("KNOWLEDGE_SHARE", new KnowledgeShare());

    @Getter
    private final String id;
    @Getter
    private final Spell spell;

    @ParametersAreNonnullByDefault
    SpellType(String id, Spell spell) {
        this.id = id;
        this.spell = spell;
    }

    @Nonnull
    public Spell get() {
        return spell;
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        this.spell.castSpell(castInformation);
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

}
