package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Fireball extends Spell {

    public Fireball() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 1, true)
            .makeProjectileSpell(this::fireProjectile, this::projectileHit, 1, false, 1, false)
            .addBeforeProjectileHitEvent(this::beforeProjectileHit)
            .makeDamagingSpell(2, true, 0.5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = new MagicProjectile(EntityType.SMALL_FIREBALL, aimLocation, castInformation.getCaster());
        magicProjectile.setVelocity(location.getDirection(), 1.5);
        registerProjectile(magicProjectile.getProjectile(), castInformation);
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(80);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            damageEntity(livingEntity, castInformation.getCaster(), getDamage(castInformation), castInformation.getDamageLocation(), getKnockback(castInformation));
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "FIREBALL";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Shoots a fireball in the direction you are",
            "looking at."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }
}
