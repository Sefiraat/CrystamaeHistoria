package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

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
import org.bukkit.event.player.PlayerTeleportEvent;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Hearthstone extends Spell {

    public Hearthstone() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(300, true, 0, false, 50, false)
            .makeTickingSpell(this::tick, 10, false, 10, false)
            .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {
        int sizeCast = 2;
        int stepSize = 3;
        Location middle = castInformation.getCasterAsPlayer().getLocation().add(0, 1, 0);
        for (double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            int sx = (int) (sizeCast * Math.cos(angle));
            int sz = (int) (sizeCast * Math.sin(angle));
            Location spawn = middle.clone().add(sx, 0, sz);
            ParticleUtils.displayParticleEffect(spawn, Particle.DRIPPING_HONEY, 0.1, 1);
        }
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        Location location = caster.getBedSpawnLocation();
        if (location == null) {
            Location casterLocation = caster.getLocation();
            ParticleUtils.displayParticleEffect(casterLocation.add(casterLocation.getDirection()), Particle.VILLAGER_ANGRY, 1, 10);
        } else {
            caster.teleportAsync(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HUMAN,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Sends you back to your last bed if",
            "possible. Takes time to cast, movement",
            "stops the cast."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "HEARTHSTONE";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.RED_BED;
    }
}
