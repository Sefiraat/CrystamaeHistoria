package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class HealingMist extends Spell {

    private static final int REGEN_DURATION = 10;
    private static final int AMPLIFICATION = 0;

    public HealingMist() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 10, false, 5, true)
            .makeInstantSpell(this::cast)
            .makeHealingSpell(0, false);
        spellCoreBuilder.addPositiveEffect(PotionEffectType.REGENERATION, AMPLIFICATION, REGEN_DURATION);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range, Player.class::isInstance)) {
            Player player = (Player) entity;
            applyPositiveEffects(player);
            displayParticleEffect(player, Particle.HEART, 2, 10);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "HEALING_MIST";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Calls fourth a mist of healing energy",
            "around the caster. Gives regen to all",
            "players affected."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }
}
