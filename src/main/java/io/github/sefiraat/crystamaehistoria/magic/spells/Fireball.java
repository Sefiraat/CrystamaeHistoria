package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class Fireball extends Spell {

    public Fireball() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 1, true)
                .makeProjectileSpell(this::fireProjectile, this::projectileHit, 1, false, 1, false)
                .addBeforeProjectileHitEvent(this::beforeProjectileHit)
                .makeDamagingSpell(2, true, 0.5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectile(@Nonnull SpellCastInformation spellCastInformation) {
        Location location = spellCastInformation.getCaster().getLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = new MagicProjectile(EntityType.SMALL_FIREBALL, aimLocation, spellCastInformation.getCaster());
        magicProjectile.setVelocity(location.getDirection(), 2);

        registerProjectile(magicProjectile.getProjectile(), spellCastInformation);

    }

    public void beforeProjectileHit(@Nonnull @NotNull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, true)) {
            livingEntity.setFireTicks(80);
        }
    }

    public void projectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, true)) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), getDamage(spellCastInformation), spellCastInformation.getDamageLocation(), getKnockback(spellCastInformation));
        }
    }

}
