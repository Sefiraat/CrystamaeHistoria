package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range, entity -> entity instanceof Player)) {
            Player player = (Player) entity;
            applyPositiveEffects(player);
            displayParticleEffect(player, Particle.HEART, 2, 10);
        }
    }

}
