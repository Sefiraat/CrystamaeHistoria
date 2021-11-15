package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Push extends Spell {

    public Push() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 30, false, 10, true)
            .makeDamagingSpell(0, false, 0.2, true)
            .makeTickingSpell(this::onTick, 5, false, 20, false)
            .addAfterTicksEvent(this::afterAllTicks);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        push(castInformation, getKnockback(castInformation));
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        push(castInformation, getKnockback(castInformation) * 3);
    }

    @ParametersAreNonnullByDefault
    private void push(CastInformation castInformation, double amount) {
        Location castLocation = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                GeneralUtils.pushEntity(castInformation.getCaster(), castLocation, entity, amount);
                ParticleUtils.displayParticleEffect(entity, Particle.CRIT, 1, 10);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "PUSH";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "In a jam? This spell gives you some room",
            "to breathe."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.PISTON;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HUMAN,
            StoryType.CELESTIAL
        );
    }
}
