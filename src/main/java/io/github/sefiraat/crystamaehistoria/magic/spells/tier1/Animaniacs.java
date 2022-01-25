package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animaniacs extends Spell {

    protected static final List<EntityType> CONVERTIBLE_LIST = Arrays.asList(
        EntityType.COW,
        EntityType.CHICKEN,
        EntityType.SHEEP,
        EntityType.COD,
        EntityType.SALMON,
        EntityType.TROPICAL_FISH,
        EntityType.BAT,
        EntityType.BEE,
        EntityType.DONKEY,
        EntityType.WOLF,
        EntityType.FOX,
        EntityType.SNOWMAN,
        EntityType.TURTLE,
        EntityType.RABBIT,
        EntityType.PARROT,
        EntityType.CAT,
        EntityType.OCELOT,
        EntityType.HORSE,
        EntityType.MUSHROOM_COW,
        EntityType.AXOLOTL,
        EntityType.PUFFERFISH,
        EntityType.PIG,
        EntityType.DOLPHIN,
        EntityType.GOAT,
        EntityType.LLAMA,
        EntityType.PANDA,
        EntityType.POLAR_BEAR
    );

    public Animaniacs() {
        final SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 0, false, 10, false)
            .makeProjectileSpell(this::fireProjectile, 5, true, 0, false)
            .makeProjectileVsBlockSpell(this::projectileHit)
            .makeProjectileVsEntitySpell(this::projectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        final Location location = castInformation.getCastLocation();
        final Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        final MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SNOWBALL, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
        magicProjectile.disableGravity();
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity entity : getTargets(castInformation, 7, true)) {
            final Location entityLocation = entity.getLocation();
            final EntityType entityType = entity.getType();
            if (CONVERTIBLE_LIST.contains(entityType)
                && GeneralUtils.hasPermission(castInformation.getCaster(), entityLocation, Interaction.INTERACT_ENTITY)
            ) {
                final EntityType convertTo = CONVERTIBLE_LIST.get(ThreadLocalRandom.current().nextInt(CONVERTIBLE_LIST.size()));
                final String name = entity.getCustomName();
                entity.remove();
                final Entity newEntity = entityLocation.getWorld().spawnEntity(entityLocation, convertTo, true);
                newEntity.setCustomName(name);
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ALCHEMICAL,
            StoryType.ANIMAL,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Disjoints the reality of nearby creatures."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "ANIMANIACS";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.AXOLOTL_SPAWN_EGG;
    }
}
