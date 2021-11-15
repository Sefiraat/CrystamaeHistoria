package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Gravity extends Spell {

    public Gravity() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(300, true, 0, false, 50, false)
            .makeTickingSpell(this::tick, 1, false, 20, false)
            .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        player.setVelocity(player.getLocation().getDirection().add(new Vector(0, 5, 0)));
        ParticleUtils.displayParticleEffect(player, Particle.GLOW_SQUID_INK, 2, 10);
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        player.setAllowFlight(true);
        player.setFlying(true);
        long expiry = System.currentTimeMillis() + ((castInformation.getStaveLevel() * 30L) * 1000);
        CrystamaeHistoria.getSpellMemory().getPlayersWithFlight().put(player.getUniqueId(), expiry);
        ParticleUtils.displayParticleEffect(player, Particle.FALLING_OBSIDIAN_TEAR, 2, 20);
    }

    @Nonnull
    @Override
    public String getId() {
        return "GRAVITY";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Allows you to use a temporary gravity",
            "pocket to fly."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FIREWORK_STAR;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ALCHEMICAL,
            StoryType.CELESTIAL
        );
    }
}
