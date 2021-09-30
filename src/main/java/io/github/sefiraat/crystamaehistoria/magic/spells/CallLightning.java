package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractDamagingProjectileSpell;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;

public class CallLightning extends AbstractDamagingProjectileSpell {

    @Override
    public void cast(@Nonnull SpellCastInformation spellCastInformation) {

        super.cast(spellCastInformation);

        Block block = spellCastInformation.getCaster().getTargetBlockExact(100);
        if (block != null) {
            Location location = block.getLocation();
            LightningStrike lightningStrike = location.getWorld().strikeLightning(location);

            registerProjectile(lightningStrike, spellCastInformation);
        }

    }

    @Override
    protected int getBaseCooldown() {
        return 50;
    }

    @Override
    protected int getBaseDamage() {
        return 2;
    }

    @Override
    protected double getRange() {
        return 100;
    }

    @Override
    protected double getKnockback() {
        return 0;
    }

    @Override
    protected double getProjectileAoeRange() {
        return 2;
    }

    @Override
    protected double getProjectileKnockbackForce() {
        return 0.5;
    }

    @Override
    public void beforeAffect(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    public void affect(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : spellCastInformation.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellCastInformation.getCaster(), spellCastInformation.getDamage(), spellCastInformation.getDamageLocation(), spellCastInformation.getKnockbackForce());
        }
    }

}
