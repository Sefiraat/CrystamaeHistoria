package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class Fireball implements CastableProjectile {

    private static final int DAMAGE = 2;
    private static final int COOLDOWN = 50;
    private static final double KNOCK_BACK_FORCE = 1;
    private static final double AOE_RANGE = 1;

    @Override
    public void cast(@Nonnull SpellDefinition spellDefinition) {

        spellDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Location location = spellDefinition.getCaster().getLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = new MagicProjectile(EntityType.SMALL_FIREBALL, aimLocation, spellDefinition.getCaster());
        magicProjectile.setVelocity(location.getDirection(), 2);

        registerProjectile(magicProjectile.getProjectile(), spellDefinition);

    }

    @Override
    public void beforeAffect(@Nonnull @NotNull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    public void affect(@Nonnull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellDefinition.getCaster(), spellDefinition.getDamage(), spellDefinition.getDamageLocation(), spellDefinition.getKnockbackForce());
        }
    }

}
