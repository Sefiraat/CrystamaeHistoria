package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Shroud extends Spell {

    public Shroud() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, true, 5, true)
            .makeDamagingSpell(0, false, 0, false)
            .makeInstantSpell(this::cast)
            .makeEffectingSpell(true, false)
            .addNegativeEffect(PotionEffectType.BLINDNESS, 1, 20)
            .addNegativeEffect(PotionEffectType.WITHER, 1, 10);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity, castInformation);
                ParticleUtils.displayParticleEffect(livingEntity, Particle.SLIME, 2, 2);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "SHROUD";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a dark miasma around the player",
            "causing light damage and blinding those",
            "affected."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.BLACK_CANDLE;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HUMAN,
            StoryType.VOID
        );
    }
}
