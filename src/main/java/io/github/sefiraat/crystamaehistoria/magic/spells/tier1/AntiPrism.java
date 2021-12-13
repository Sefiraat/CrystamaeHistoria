package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class AntiPrism extends Spell {

    public AntiPrism() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 0, false, 100, true)
            .makeProjectileSpell(this::cast, 3, false, 0, false)
            .makeProjectileVsEntitySpell(this::projectileHit)
            .makeProjectileVsBlockSpell(this::projectileHit)
            .makeEffectingSpell(true, true)
            .addNegativeEffect(PotionEffectType.BAD_OMEN, 1, 30)
            .addNegativeEffect(PotionEffectType.BLINDNESS, 1, 30)
            .addNegativeEffect(PotionEffectType.CONFUSION, 1, 30)
            .addNegativeEffect(PotionEffectType.HARM, 1, 30)
            .addNegativeEffect(PotionEffectType.HUNGER, 1, 30)
            .addNegativeEffect(PotionEffectType.POISON, 1, 30)
            .addNegativeEffect(PotionEffectType.SLOW, 1, 30)
            .addNegativeEffect(PotionEffectType.SLOW_DIGGING, 1, 30)
            .addNegativeEffect(PotionEffectType.UNLUCK, 1, 30)
            .addNegativeEffect(PotionEffectType.WEAKNESS, 1, 30)
            .addNegativeEffect(PotionEffectType.WITHER, 1, 30);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SPLASH_POTION, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 0.5);
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        final double range = getProjectileAoe(castInformation);

        for (double height = 0; height <= Math.PI; height += Math.PI / 10) {
            final double r = range * Math.sin(height);
            final double y = range * Math.cos(height);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                final double x = Math.cos(a) * r;
                final double z = Math.sin(a) * r;
                final Location point = castInformation.getProjectileLocation().clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.SOUL, 0.1, 1);
            }
        }

        for (LivingEntity entity : getTargets(castInformation, range, true)) {
            if (PersistentDataAPI.getBoolean(entity, Keys.newKey("PRISM"))) {
                entity.damage(200);
            }
            PersistentDataAPI.setBoolean(entity, Keys.newKey("ANTIPRISM"), true);
            applyNegativeEffects(entity, castInformation);
            ParticleUtils.displayParticleEffect(entity, Particle.CRIMSON_SPORE, range, 20);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.HISTORICAL,
            StoryType.ANIMAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Applies a myriad of negative effects to",
            "enemies hit."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "ANTI_PRISM";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.POTION;
    }
}
