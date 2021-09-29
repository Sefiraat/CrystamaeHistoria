package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.AbstractSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;

public class CallLightning extends AbstractSpell implements CastableProjectile {

    private static final int DAMAGE = 10;
    private static final int COOLDOWN = 200;
    private static final double KNOCK_BACK_FORCE = 5;
    private static final double AOE_RANGE = 5;

    @Override
    public void cast(@Nonnull CastDefinition castDefinition) {
        super.cast(castDefinition);

        castDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Block block = castDefinition.getCaster().getTargetBlockExact(100);
        if (block != null) {
            Location location = block.getLocation();
            LightningStrike lightningStrike = location.getWorld().strikeLightning(location);

            registerProjectile(lightningStrike, castDefinition);
        }

    }

    @Override
    public void beforeAffect(@Nonnull CastDefinition castDefinition) {
        for (LivingEntity livingEntity : castDefinition.getAllTargets()) {
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    public void affect(@Nonnull CastDefinition castDefinition) {
        for (LivingEntity livingEntity : castDefinition.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, castDefinition.getCaster(), castDefinition.getDamage(), castDefinition.getDamageLocation(), castDefinition.getKnockbackForce());
        }
    }

}
