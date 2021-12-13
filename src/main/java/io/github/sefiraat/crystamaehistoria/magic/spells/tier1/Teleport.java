package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class Teleport extends Spell {

    public Teleport() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 20, false, 1, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        if (caster != null) {
            Location teleportToLocation = getTeleportLocation(caster, getRange(castInformation));

            if (teleportToLocation != null) {
                caster.teleport(teleportToLocation);
                ParticleUtils.displayParticleEffect(caster, Particle.END_ROD, 1, 10);
            } else {
                caster.sendMessage(CrystamaeHistoria.getInstance().getConfig().getString("messages.spells.teleport_no_suitable_location"));
            }
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private Location getTeleportLocation(Player player, double range) {
        Location location = player.getLocation().add(player.getLocation().getDirection().setY(0).multiply(range));
        if (location.getBlock().getType() == Material.AIR) {
            return location.add(0, 1, 0);
        } else {
            return tryIncreaseY(location, 1);
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
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

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.HISTORICAL,
            StoryType.VOID
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Teleports the caster x blocks forward."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "TELEPORT";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.ENDER_PEARL;
    }
}
