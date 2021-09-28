package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableInstant;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Teleport implements CastableInstant {

    private static final int DISTANCE = 20;

    @Override
    public void cast(@Nonnull SpellDefinition spellDefinition) {

        Player caster = spellDefinition.getCaster();

        Location teleportToLocation = getTeleportLocation(caster);

        if (teleportToLocation != null) {
            caster.teleport(teleportToLocation);
        } else {
            caster.sendMessage(CrystamaeHistoria.config().getString("messages.spells.teleport_no_suitable_location"));
        }

    }

    @Nullable
    private Location getTeleportLocation(@Nonnull Player player) {
        Location location = player.getLocation().add(player.getLocation().getDirection().setY(0).multiply(DISTANCE));
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
