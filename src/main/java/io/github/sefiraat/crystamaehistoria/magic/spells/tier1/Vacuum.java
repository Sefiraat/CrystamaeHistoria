package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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
                GeneralUtils.pullEntity(castInformation.getCaster(), castLocation, entity, amount);
                ParticleUtils.displayParticleEffect(entity, Particle.CRIT, 1, 10);
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

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FISHING_ROD;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HUMAN,
            StoryType.VOID
        );
    }
}
