package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Teleport extends Spell {

    public Teleport() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 20, false, 1, true)
                .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    public void cast(@NonNull CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        if (caster != null) {
            Location teleportToLocation = getTeleportLocation(caster, getRange(castInformation));

            if (teleportToLocation != null) {
                caster.teleport(teleportToLocation);
                displayParticleEffect(caster, Particle.END_ROD, 1, 10);
            } else {
                caster.sendMessage(CrystamaeHistoria.config().getString("messages.spells.teleport_no_suitable_location"));
            }
        }
    }

    @Nullable
    private Location getTeleportLocation(@Nonnull Player player, double range) {
        Location location = player.getLocation().add(player.getLocation().getDirection().setY(0).multiply(range));
        if (location.getBlock().getType() == Material.AIR) {
            return location.add(0, 1, 0);
        } else {
            return tryIncreaseY(location, 1);
        }
    }

    @Nullable
    private Location tryIncreaseY(Location location, int attemptNumber) {
        if (attemptNumber >= 10) {
            return null;
        } else {
            Location newLocation = location.clone().add(0, 1, 0);
            if (newLocation.getBlock().getType() == Material.AIR) {
                return location;
            } else {
                return tryIncreaseY(newLocation, attemptNumber + 1);
            }
        }
    }

}
