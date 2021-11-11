package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PoisonNova extends Spell {

    public PoisonNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 20, true, 10, true)
            .makeDamagingSpell(1, false, 0, false)
            .makeProjectileSpell(this::fireProjectile, 0, false, 0, false)
            .makeProjectileVsEntitySpell(this::projectileHit)
            .addAfterProjectileHitEntityEvent(this::afterProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        double sizeEnd = getRange(castInformation);
        int sizeCast = 1;
        int stepSize = 3;
        Location middle = castInformation.getCastLocation().clone().add(0, 1, 0);
        for (double i = 0; i < 360; i += stepSize) {
            double angle = (i * Math.PI / 180);
            double sx = sizeCast * Math.cos(angle);
            double sz = sizeCast * Math.sin(angle);
            double dx = sizeEnd * Math.cos(angle);
            double dz = sizeEnd * Math.sin(angle);
            Location spawn = middle.clone().add(sx, 0, sz);
            Location destination = middle.clone().add(dx, 1, dz);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.ENDER_PEARL, spawn);
            magicProjectile.setVelocity(destination, 1);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        LivingEntity hit = castInformation.getMainTarget();
        if (hit.getHealth() == 1) {
            GeneralUtils.damageEntity(hit, castInformation.getCaster(), getDamage(castInformation));
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON,
                castInformation.getStaveLevel() * 100,
                castInformation.getStaveLevel()
            );
            hit.addPotionEffect(potionEffect);
            setLastDamageToCaster(hit, castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void afterProjectileHit(CastInformation castInformation) {
        ParticleUtils.displayParticleEffect(castInformation.getMainTarget(),
            Particle.CRIMSON_SPORE,
            1.0,
            10
        );
    }

    @Nonnull
    @Override
    public String getId() {
        return "POISON_NOVA";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a nova of poisonous bullets that",
            "tear through enemies and cause them to",
            "get sick."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SLIME_BALL;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HUMAN,
            StoryType.ANIMAL
        );
    }
}
