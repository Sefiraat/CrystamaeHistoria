package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Vacuum extends Spell {

    public Vacuum() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 10, true)
            .makeDamagingSpell(0, false, 0.2, true)
            .makeTickingSpell(this::onTick, 5, false, 20, false)
            .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        pull(castInformation, getKnockback(castInformation));
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        pull(castInformation, getKnockback(castInformation) * 3);
    }

    @ParametersAreNonnullByDefault
    private void pull(CastInformation castInformation, double amount) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        Location castLocation = caster.getLocation();
        double range = getRange(castInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                pullEntity(castLocation, entity, amount);
                displayParticleEffect(entity, Particle.CRIT, 1, 10);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "VACUUM";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Feeling lonely? This spell lets you get up",
            "close and personal will all nearby things."
        };
    }

    @NotNull
    @Override
    public Material getMaterial() {
        return Material.FISHING_ROD;
    }
}
